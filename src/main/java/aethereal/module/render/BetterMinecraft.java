package aethereal.module.render;

import aethereal.core.Category;
import aethereal.core.EventTarget;
import aethereal.core.Module;
import aethereal.core.ModuleRegister;
import aethereal.event.AmbienceEvent;
import aethereal.setting.ModeSetting;

@ModuleRegister(a = "BetterMinecraft", b = "Меняет время и осадки для более выразительного вида мира", c = Category.Render)
public class BetterMinecraft extends Module {
    private final ModeSetting time = new ModeSetting("Время", "Без изменений", "Без изменений", "Рассвет", "День", "Закат", "Ночь");
    private final ModeSetting weather = new ModeSetting("Погода", "Без изменений", "Без изменений", "Ясно", "Дождь", "Гроза");

    public BetterMinecraft() {
        a(this.time, this.weather);
    }

    @EventTarget
    public void onTime(AmbienceEvent.c event) {
        switch (this.time.c()) {
            case "Рассвет" -> event.a(23041L);
            case "День" -> event.a(6000L);
            case "Закат" -> event.a(12610L);
            case "Ночь" -> event.a(18000L);
            default -> { }
        }
    }

    @EventTarget
    public void onWeather(AmbienceEvent.d event) {
        if (this.weather.l("Ясно")) {
            if (event.b() == AmbienceEvent.d.a.RAIN_GRADIENT || event.b() == AmbienceEvent.d.a.THUNDER_GRADIENT || event.b() == AmbienceEvent.d.a.PRECIPITATION_PARTICLES) {
                event.a(0.0f);
            }
        } else if (this.weather.l("Дождь") || this.weather.l("Гроза")) {
            if (event.b() == AmbienceEvent.d.a.RAIN_GRADIENT) event.a(1.0f);
            if (event.b() == AmbienceEvent.d.a.THUNDER_GRADIENT) event.a(this.weather.l("Гроза") ? 1.0f : 0.0f);
        }
    }
}
