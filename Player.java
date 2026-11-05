/**
 * Abstract base class representing a game player.
 * @author Archange DESTINE
 * @author Kosai Zain Aldin
 */

public abstract class Player implements Displayable {
    private String displayName;
    private Avatar avatar;

    // TO-DO
    // Constructor that initializes a player with a name and avatar.
    // The player’s Display Name must be a valid one (length between 2 and 15 inclusive) 
    // and no bad word used as part of the Display Name.
    // For invalid argument, throw IllegalArgumentException
    public Player(String displayName, Avatar avatar) throws IllegalArgumentException {
        if (displayName == null || displayName.length() < 2 || displayName.length() > 15|| Utility.containsBadWord(displayName,  "bad_words.txt") ){
            throw new IllegalArgumentException("Invalid display name");
        }
        else{
            this.displayName = displayName;
        }
        if (avatar == null){
            this.avatar = Avatar.AVATAR_A;
        }
        else {
            this.avatar = avatar;
        }

    }

    public Player(String displayName) throws IllegalArgumentException {
        this(displayName, null);
    }

    public Avatar getAvatar() {
        return this.avatar;
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }
}


