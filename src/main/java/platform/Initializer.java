package platform;


import aethereal.core.Epsilon;
import aethereal.core.NativeMethodLookup;
import aethereal.api.Compile;
import net.fabricmc.api.ClientModInitializer;

public class Initializer implements ClientModInitializer {
    @Compile
    public void onInitializeClient() {
        new Epsilon();
    }

    static {
        NativeMethodLookup.lookup(Initializer.class, 1);
    }
}
