package src.Bussines;

import java.util.List;

public interface Strategy {
    void performAction (Character character, List<Item> items, Team enemyTeam, StringBuilder messageRound, ManagerCharacter managerCharacter);
}
