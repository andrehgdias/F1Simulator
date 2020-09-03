package sample;

public class Event {
    public static void rain(Team[] teams) {
        // mandar todos os carros trocar de pneus
        GrandPrix grandPrix = DashboardController.getChampionship().getGrandPrixs().get(DashboardController.getNextGrandPrix());
        String type;
        if (grandPrix.getWeather().equals("Limpo") || grandPrix.getWeather().equals("Nublado")) {
            System.out.println(" > Started rainnig at Grand Prix " + grandPrix.getCity());
            grandPrix.setWeather("Chuva");
            type= "Macio";
        } else {
            System.out.println(" > Stopped rainnig at Grand Prix " + grandPrix.getCity());
            grandPrix.setWeather("Nublado");
            type= "Duro";
        }

        for(Team team: teams) {
            team.getPilots()[0].getEngineer().OrderChangeTyres(type);
            team.getPilots()[1].getEngineer().OrderChangeTyres(type);
        }
    }

    public static void penalty(Pilot pilot) {
        pilot.getEngineer().InformPenality();
    }

    public static void refueling(Pilot pilot) {
        pilot.getEngineer().OrderRefueling();
    }
}
