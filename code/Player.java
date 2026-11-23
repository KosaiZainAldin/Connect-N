/**
* Abstract base class representing a game player.
* @author Kosai Zain Aldin
*/

public abstract class Player implements Displayable {
    private String displayName;
    private Avatar avatar;


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

// Professor-provided getAvatar implementation removed.


// Professor-provided getDisplayName implementation removed.

}
