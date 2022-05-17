/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package twitter;

/**
 *
 * @author aamir
 */
public class Following {
    private int id;
    private int followed_by_user_id;
    private int following_user_id;

    public Following(int id, int followed_by_user_id, int following_user_id) {
        this.id = id;
        this.followed_by_user_id = followed_by_user_id;
        this.following_user_id = following_user_id;
    }

    public int getId() {
        return id;
    }

    public int getFollowed_by_user_id() {
        return followed_by_user_id;
    }

    public int getFollowing_user_id() {
        return following_user_id;
    }
}
