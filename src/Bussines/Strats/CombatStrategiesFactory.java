package src.Bussines.Strats;

public class CombatStrategiesFactory {
    public static CombatStrategies getStrategy(String strategy) {
        if (strategy.equalsIgnoreCase("Balanced")) {
            return new Balanced();
        } else if (strategy.equalsIgnoreCase("Offensive")) {
            return new Offensive();
        } else if (strategy.equalsIgnoreCase("Defensive")) {
            return new Defensive();
        } else if (strategy.equalsIgnoreCase("Sniper")) {
            return new Sniper();
        } else {
            // En caso de no encontrar ninguna, seteamos Balanced de default
            return new Balanced();
        }
    }
}
