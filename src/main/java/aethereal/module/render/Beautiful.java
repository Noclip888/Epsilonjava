package aethereal.module.render;

import aethereal.core.Category;
import aethereal.core.EventTarget;
import aethereal.core.Module;
import aethereal.core.ModuleRegister;
import aethereal.event.AmbienceEvent;
import aethereal.render.ColorUtil;
import aethereal.setting.ColorSetting;

@ModuleRegister(a = "Beautiful", b = "Настраивает мягкий цвет неба и визуальную атмосферу", c = Category.Render)
public class Beautiful extends Module {
    private final ColorSetting skyColor = new ColorSetting("Цвет неба", ColorUtil.a(132, 153, 255, 255));

    public Beautiful() {
        a(this.skyColor);
    }

    @EventTarget
    public void onClearColor(AmbienceEvent.a event) {
        float[] rgba = ColorUtil.a(this.skyColor.c().intValue());
        event.a(rgba[0]);
        event.b(rgba[1]);
        event.c(rgba[2]);
        event.d(rgba[3]);
        event.a(true);
    }
}
