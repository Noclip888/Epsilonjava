package aethereal.module.render;

import aethereal.core.Category;
import aethereal.core.EventTarget;
import aethereal.core.Epsilon;
import aethereal.core.Interface;
import aethereal.core.Module;
import aethereal.core.ModuleRegister;
import aethereal.event.DrawEvent;
import aethereal.event.TickEvent;
import aethereal.config.ThemeInfo;
import aethereal.setting.SliderSetting;
import java.util.ArrayDeque;
import java.util.Deque;
import net.minecraft.util.math.Vec3d;

@ModuleRegister(a = "Trails", b = "Оставляет плавный цветной след за движением игрока", c = Category.Render)
public class Trails extends Module implements Interface {
    private final SliderSetting length = new SliderSetting("Длина следа", 32.0f, 8.0f, 96.0f, 1.0f);
    private final Deque<Vec3d> points = new ArrayDeque<>();

    public Trails() {
        a(this.length);
    }

    @EventTarget
    public void onTick(TickEvent event) {
        if (aM_.player == null || aM_.world == null) return;
        Vec3d pos = aM_.player.getPos().add(0.0, 0.08, 0.0);
        if (this.points.isEmpty() || this.points.getLast().squaredDistanceTo(pos) > 0.0025) this.points.addLast(pos);
        while (this.points.size() > this.length.c().intValue()) this.points.removeFirst();
    }

    @EventTarget
    public void onRender(DrawEvent event) {
        if (!event.c() || this.points.size() < 2) return;
        Vec3d previous = null;
        for (Vec3d point : this.points) {
            if (previous != null) {
                Vec3d control = previous.lerp(point, 0.5);
                Epsilon.h().d().j().a(event.h(), previous, point, control, Epsilon.h().d().o().a(ThemeInfo.PRIMARY).a(), 1.4f);
            }
            previous = point;
        }
    }

    @Override
    public void c() {
        super.c();
        this.points.clear();
    }
}
