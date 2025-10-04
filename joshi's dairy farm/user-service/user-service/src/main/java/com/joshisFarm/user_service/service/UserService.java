package com.joshisFarm.user_service.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.joshisFarm.user_service.client.AuthClient;
import com.joshisFarm.user_service.client.NotificationClient;
import com.joshisFarm.user_service.dto.*;
import com.joshisFarm.user_service.entity.Attendence;
import com.joshisFarm.user_service.entity.User;
import com.joshisFarm.user_service.repository.AttendanceRepository;
import com.joshisFarm.user_service.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
    private final AttendanceRepository attendanceRepository;
    private final AuthClient authClient;
    private final NotificationClient notificationClient;

    // ✅ Role-based Create User
    public UserDTO createUser(UserDTO dto, String token) {
        UserResponseDTO authUser = authClient.validateToken(token);

//        // Only OWNER or MANAGER can create users
//        if (!(authUser.getRole().equals("OWNER") || authUser.getRole().equals("MANAGER"))) {
//            throw new RuntimeException("Access denied: Only Owner or Manager can create users.");
//        }

        User user = User.builder()
                .name(dto.getName())
                .location(dto.getLocation())
                .earning(dto.getEarning())
                .cowsCount(dto.getCowsCount())
                .companyId(dto.getCompanyId())
                .role(dto.getRole())
                .build();

        user = userRepository.save(user);

        // Send notification
        notificationClient.sendNotification("New user added: " + user.getName());

        return mapToDTO(user, authUser);
    }

    // ✅ Get all users for a company
    public List<UserDTO> getUsersByCompany(Long companyId, String token) {
        UserResponseDTO authUser = authClient.validateToken(token);
        return userRepository.findByCompanyId(companyId)
                .stream()
                .map(user -> mapToDTO(user, authUser))
                .collect(Collectors.toList());
    }

    // ✅ Profile update (user can update their own profile)
    public UserDTO updateProfile(Long userId, UserDTO dto, String token) {
        UserResponseDTO authUser = authClient.validateToken(token);

        if (!authUser.getId().equals(userId) && !authUser.getRole().equals("OWNER")) {
            throw new RuntimeException("Access denied: Only owner or the user themselves can update profile.");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setName(dto.getName());
        user.setLocation(dto.getLocation());
        user.setCowsCount(dto.getCowsCount());

        user = userRepository.save(user);
        return mapToDTO(user, authUser);
    }

 // ✅ Mark attendance with stricter rules
    public AttendanceDTO markAttendance(Long userId, boolean present, String token) {
        UserResponseDTO authUser = authClient.validateToken(token);

        // Find target user (whose attendance is being marked)
        User targetUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // If role is WORKER/VET/DISTRIBUTOR -> can only mark their own attendance
        if (authUser.getRole().equals("WORKER") ||
            authUser.getRole().equals("VET") ||
            authUser.getRole().equals("DISTRIBUTOR")) {

            if (!authUser.getId().equals(userId)) {
                throw new RuntimeException("Access denied: You can only mark your own attendance.");
            }
        }

        // If role is OWNER/MANAGER -> can mark attendance for any user in their company
        if (authUser.getRole().equals("OWNER") ||
            authUser.getRole().equals("MANAGER")) {

            if (!authUser.getId().equals(userId)) {
                // check same company
                if (!targetUser.getCompanyId().equals(
                        userRepository.findById(authUser.getId())
                        .map(User::getCompanyId)
                        .orElseThrow(() -> new RuntimeException("Auth user company not found")))) {
                    throw new RuntimeException("Access denied: You can only mark attendance within your company.");
                }
            }
        }

        Attendence attendance = Attendence.builder()
                .userId(userId)
                .date(LocalDate.now())
                .present(present)
                .build();

        attendance = attendanceRepository.save(attendance);

        return AttendanceDTO.builder()
                .id(attendance.getId())
                .userId(attendance.getUserId())
                .date(attendance.getDate())
                .present(attendance.isPresent())
                .build();
    }


    // ✅ Convert entity -> DTO (with role-based earnings visibility)
    private UserDTO mapToDTO(User user, UserResponseDTO authUser) {
        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .location(user.getLocation())
                .earning(authUser.getRole().equals("OWNER") ? user.getEarning() : null)
                .cowsCount(user.getCowsCount())
                .companyId(user.getCompanyId())
                .role(user.getRole())
                .build();
    }
}
