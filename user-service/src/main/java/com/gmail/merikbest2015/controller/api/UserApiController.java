package com.gmail.merikbest2015.controller.api;

import com.gmail.merikbest2015.commons.dto.commons_new.UserIdsRequest;
import com.gmail.merikbest2015.commons.dto.commons_new.ListMemberResponse;
import com.gmail.merikbest2015.commons.dto.commons_new.ListOwnerResponse;
import com.gmail.merikbest2015.commons.models.User;
import com.gmail.merikbest2015.repository.projection.UserChatProjection;
import com.gmail.merikbest2015.service.UserClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.gmail.merikbest2015.commons.controller.PathConstants.API_V1_USER;

@RestController
@RequiredArgsConstructor
@RequestMapping(API_V1_USER)
public class UserApiController {

    private final UserClientService userService;

    @GetMapping("/{userId}")
    public Optional<User> getUserById(@PathVariable("userId") Long userId) {
        return userService.getUserById(userId);
    }

    @PostMapping("/ids")
    public List<User> getUsersByIds(@RequestBody UserIdsRequest request) {
        return userService.getUsersByIds(request);
    }

    @GetMapping("/ids")
    public List<Long> getUserFollowersIds() {
        return userService.getUserFollowersIds();
    }

    @GetMapping("/search/{username}")
    public Page<UserChatProjection> searchUsersByUsername(@PathVariable("username") String username, Pageable pageable) {
        return userService.searchUsersByUsername(username, pageable);
    }

    @GetMapping("/valid/{userId}/{authUserId}")
    public User getValidUser(@PathVariable("userId") Long userId, @PathVariable("authUserId") Long authUserId) {
        return userService.getValidUser(userId, authUserId);
    }

    @GetMapping("/notification/user/{authUserId}")
    public User getAuthNotificationUser(@PathVariable("authUserId") Long authUserId) {
        return userService.getAuthNotificationUser(authUserId);
    }

    @GetMapping("/subscribers/{userId}")
    public List<User> getSubscribersByUserId(@PathVariable("userId") Long userId) {
        return userService.getSubscribersByUserId(userId);
    }

    @GetMapping("/is_followed/{userId}")
    public Boolean isUserFollowByOtherUser(@PathVariable("userId") Long userId) {
        return userService.isUserFollowByOtherUser(userId);
    }

    @GetMapping("/is_private/{userId}")
    public Boolean isUserHavePrivateProfile(@PathVariable("userId") Long userId) {
        return userService.isUserHavePrivateProfile(userId);
    }

    @GetMapping("/is_muted/{userId}")
    public Boolean isUserMutedByMyProfile(@PathVariable("userId") Long userId) {
        return userService.isUserMutedByMyProfile(userId);
    }

    @GetMapping("/is_blocked/{userId}/{blockedUserId}")
    public Boolean isUserBlocked(@PathVariable("userId") Long userId, @PathVariable("blockedUserId") Long blockedUserId) {
        return userService.isUserBlocked(userId, blockedUserId);
    }

    @GetMapping("/is_user_blocked/{userId}")
    public Boolean isUserBlockedByMyProfile(@PathVariable("userId") Long userId) {
        return userService.isUserBlockedByMyProfile(userId);
    }

    @GetMapping("/is_my_profile_blocked/{userId}")
    public Boolean isMyProfileBlockedByUser(@PathVariable("userId") Long userId) {
        return userService.isMyProfileBlockedByUser(userId);
    }

    @GetMapping("/is_approved/{userId}")
    public Boolean isMyProfileWaitingForApprove(@PathVariable("userId") Long userId) {
        return userService.isMyProfileWaitingForApprove(userId);
    }

    @GetMapping("/notification/{userId}")
    public void increaseNotificationsCount(@PathVariable("userId") Long userId) {
        userService.increaseNotificationsCount(userId);
    }

    @GetMapping("/like/count/{increaseCount}")
    public void updateLikeCount(@PathVariable("increaseCount") boolean increaseCount) {
        userService.updateLikeCount(increaseCount);
    }

    @GetMapping("/tweet/count/{increaseCount}")
    public void updateTweetCount(@PathVariable("increaseCount") boolean increaseCount) {
        userService.updateTweetCount(increaseCount);
    }

    @GetMapping("/media/count/{increaseCount}")
    public void updateMediaTweetCount(@PathVariable("increaseCount") boolean increaseCount) {
        userService.updateMediaTweetCount(increaseCount);
    }

    @PostMapping
    public void saveUser(@RequestBody User user) {
        userService.saveUser(user);
    }

    // NEW
    @GetMapping("/list/owner/{userId}")
    public ListOwnerResponse getListOwnerById(@PathVariable("userId") Long userId) {
        return userService.getListOwnerById(userId);
    }

    @PostMapping("/list/participants")
    public List<ListMemberResponse> getListParticipantsByIds(@RequestBody UserIdsRequest request) {
        return userService.getListParticipantsByIds(request);
    }

    @GetMapping("/list/participants/{username}")
    public List<ListMemberResponse> searchListMembersByUsername(@PathVariable("username") String username) {
        return userService.searchListMembersByUsername(username);
    }
}
