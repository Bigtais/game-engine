package Gameplay;

import Kernel.Kernel;

public class Gameplay {

    public static Kernel kernel = Kernel.getKernel();
    public void launch(){
        new Thread(() -> kernel.launchKernel()).start();
    }

}
