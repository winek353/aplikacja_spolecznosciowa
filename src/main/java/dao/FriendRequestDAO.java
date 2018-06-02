package dao;


import model.FriendRequest;

public interface FriendRequestDAO {
    void save(FriendRequest friendRequest);
    FriendRequest getFriendRequest(int friendRequestId);
    FriendRequest getFriendRequest(int userId, int friendId);
    void delete(FriendRequest friendRequest);
}
