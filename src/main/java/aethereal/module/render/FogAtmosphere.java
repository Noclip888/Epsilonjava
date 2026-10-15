package aethereal.module.render;

import aethereal.core.Category;
import aethereal.core.EventTarget;
import aethereal.core.Module;
import aethereal.core.ModuleRegister;
import aethereal.event.AmbienceEvent;
import aethereal.render.ColorUtil;
import aethereal.setting.ColorSetting;
import aethereal.setting.SliderSetting;
import net.minecraft.block.enums.CameraSubmersionType;
import net.minecraft.client.render.FogShape;
import net.minecraft.util.math.MathHelper;

@ModuleRegister(a = "FogAtmosphere", b = "Создаёт настраиваемый цветной туман с плавной дальностью", c = Category.Render)
public class FogAtmosphere extends Module {
    private final ColorSetting fogColor = new ColorSetting("Цвет тумана", ColorUtil.a(135, 154, 190, 210));
    private final SliderSetting start = new SliderSetting("Начало тумана", 8.0f, 0.0f, 32.0f, 1.0f);
    private final SliderSetting density = new SliderSetting("Плотность", 55.0f, 5.0f, 100.0f, 1.0f);

    public FogAtmosphere() {
        a(this.fogColor, this.start, this.density);
    }

    @EventTarget
    public void onFog(AmbienceEvent.b event) {
        if (event.b().getSubmersionType() != CameraSubmersionType.NONE) return;
        float view = Math.max(1.0f, event.c());
        float[] rgba = ColorUtil.a(this.fogColor.c().intValue());
        float fogStart = MathHelper.clamp(this.start.c(), 0.0f, view * 0.7f);
        float fogEnd = MathHelper.clamp(fogStart + (view * (this.density.c() / 100.0f)), fogStart + 1.0f, view);
        event.a(fogStart, fogEnd, FogShape.SPHERE, rgba[0], rgba[1], rgba[2], rgba[3]);
    }
}
