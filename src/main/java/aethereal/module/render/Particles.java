package aethereal.module.render;

import aethereal.core.Category;
import aethereal.core.EventTarget;
import aethereal.core.Interface;
import aethereal.core.Module;
import aethereal.core.ModuleRegister;
import aethereal.event.TickEvent;
import aethereal.setting.SliderSetting;
import net.minecraft.particle.ParticleTypes;

@ModuleRegister(a = "Particles", b = "Добавляет лёгкие атмосферные частицы вокруг игрока", c = Category.Render)
public class Particles extends Module implements Interface {
    private final SliderSetting amount = new SliderSetting("Плотность", 2.0f, 1.0f, 6.0f, 1.0f);

    public Particles() {
        a(this.amount);
    }

    @EventTarget
    public void onTick(TickEvent event) {
        if (aM_.player == null || aM_.world == null || aM_.player.age % 2 != 0) return;
        for (int i = 0; i < this.amount.c().intValue(); i++) {
            double angle = Math.random() * (Math.PI * 2.0);
            double radius = 1.0 + Math.random() * 3.0;
            double x = aM_.player.getX() + Math.cos(angle) * radius;
            double y = aM_.player.getY() + 0.4 + Math.random() * 1.8;
            double z = aM_.player.getZ() + Math.sin(angle) * radius;
            aM_.world.addParticle(ParticleTypes.END_ROD, x, y, z, 0.0, 0.015, 0.0);
        }
    }
}
