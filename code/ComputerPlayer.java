/**
* Subclass of Player representing the AI/Computer player.
* @author Kosai Zain Aldin
*/
public class ComputerPlayer extends Player {


    public ComputerPlayer(String displayName, Avatar avatar) throws IllegalArgumentException {
        super(displayName,avatar);
    }

public ComputerPlayer(String displayName) throws IllegalArgumentException {
    super(displayName, null);
}

}
