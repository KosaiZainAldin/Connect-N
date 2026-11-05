/**
* Subclass of Player representing the AI/Computer player.
* @author Archange DESTINE
* @author Kosai Zain Aldin
*/
public class ComputerPlayer extends Player {

    // TO-DO
    // Constructor that calls the parent class constructor to initialize the computer player with a name and avatar.
    public ComputerPlayer(String displayName, Avatar avatar) throws IllegalArgumentException {
        super(displayName,avatar);
    }

public ComputerPlayer(String displayName) throws IllegalArgumentException {
    super(displayName, null);
}

}
