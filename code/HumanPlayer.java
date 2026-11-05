/**
* Subclass of Player representing a human player.
* @author Archange DESTINE
* @author Kosai Zain Aldin
*/

public class HumanPlayer extends Player {

    // TO-DO
    // Constructor that calls the parent class constructor to initialize the human player with a name and avatar
    public HumanPlayer(String displayName, Avatar avatar) throws IllegalArgumentException {
        super(displayName, avatar);
    }

public HumanPlayer(String displayName) throws IllegalArgumentException {
    this(displayName, null);
}

}
