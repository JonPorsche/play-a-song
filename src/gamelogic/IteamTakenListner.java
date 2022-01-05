package gamelogic;

public class IteamTakenListner implements IteamTaken {
    @Override
    public void IteamTaken(double before, double after) {
        System.out.println("Iteam Value"+ after);

    }
}
