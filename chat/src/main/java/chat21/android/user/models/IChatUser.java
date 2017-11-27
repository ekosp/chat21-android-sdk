package chat21.android.user.models;

import java.io.Serializable;

/**
 * Created by frontiere21 on 27/09/16.
 */
public interface IChatUser extends Serializable {
    void setId(String id);

    String getId();

    void setFullName(String fullName);

    String getFullName();

    void setEmail(String email);

    String getEmail();

    String getProfilePictureUrl();

    void setProfilePictureUrl(String profilePictureUrl);

    String getAuth();

    void setAuth(String auth);

    @Override
    String toString();

    int compareTo(IChatUser another);
}
