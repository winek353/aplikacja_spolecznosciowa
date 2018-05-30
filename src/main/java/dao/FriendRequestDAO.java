package dao;


import model.FriendRequest;

public interface FriendRequestDAO {
    void save(FriendRequest friendRequest);
    FriendRequest getFriendRequest(int friendRequestId);
    void delete(FriendRequest friendRequest);
}
