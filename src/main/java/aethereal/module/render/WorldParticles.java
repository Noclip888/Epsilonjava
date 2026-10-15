package aethereal.module.render;

import aethereal.core.Category;
import aethereal.core.EventTarget;
import aethereal.core.Interface;
import aethereal.core.Module;
import aethereal.core.ModuleRegister;
import aethereal.event.BlockChangeEvent;
import aethereal.setting.SliderSetting;
import net.minecraft.block.Blocks;
import net.minecraft.particle.ParticleTypes;

@ModuleRegister(a = "World Particles", b = "Добавляет искры при изменении и разрушении блоков", c = Category.Render)
public class WorldParticles extends Module implements Interface {
    private final SliderSetting amount = new SliderSetting("Количество частиц", 7.0f, 1.0f, 16.0f, 1.0f);

    public WorldParticles() {
        a(this.amount);
    }

    @EventTarget
    public void onBlockChange(BlockChangeEvent event) {
        if (aM_.world == null || aM_.player == null || !event.d().isAir() || event.c().isAir()) return;
        if (aM_.player.squaredDistanceTo(event.b().toCenterPos()) > 32.0 * 32.0) return;
        var particle = event.c().isOf(Blocks.CANDLE) ? ParticleTypes.FLAME : ParticleTypes.WAX_OFF;
        for (int i = 0; i < this.amount.c().intValue(); i++) {
            double x = event.b().getX() + Math.random();
            double y = event.b().getY() + Math.random();
            double z = event.b().getZ() + Math.random();
            aM_.world.addParticle(particle, x, y, z, (Math.random() - 0.5) * 0.04, Math.random() * 0.05, (Math.random() - 0.5) * 0.04);
        }
    }
}
