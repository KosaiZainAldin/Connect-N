/**
* Subclass of Player representing a human player.
* @author Kosai Zain Aldin
*/

public class HumanPlayer extends Player {


    public HumanPlayer(String displayName, Avatar avatar) throws IllegalArgumentException {
        super(displayName, avatar);
    }

public HumanPlayer(String displayName) throws IllegalArgumentException {
    this(displayName, null);
}

}
