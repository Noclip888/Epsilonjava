package aethereal.ui.screen;

import aethereal.render.ScaleUtil;
import aethereal.core.NativeMethodLookup;
import aethereal.ui.shader.GradientUtil;
import aethereal.ui.widget.EffectMarker;
import static aethereal.core.Interface.aM_;
import aethereal.core.Epsilon;
import aethereal.core.Interface;
import aethereal.render.EasingList;
import aethereal.render.Fonts;
import aethereal.render.ColorUtil;
import aethereal.util.MathUtil;

import aethereal.config.ThemeInfo;
import aethereal.render.Draw2DProcessor;
import aethereal.ui.screen.AltScreen;

import aethereal.render.AnimationUtil;
import aethereal.ui.element.Button;
import aethereal.api.Compile;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.math.MathHelper;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.gui.screen.world.SelectWorldScreen;
import net.minecraft.sound.MusicSound;
import net.minecraft.sound.MusicType;

public class MainScreen extends Screen {
    private static final float[] a;
    private static final String SPLASH_TEXT = "Welcome to Epsilon Client";
    private static final float SPLASH_FADE_IN = 480.0f;
    private static final float SPLASH_TYPE_START = 320.0f;
    private static final float SPLASH_TYPE_TIME = 2800.0f;
    private static final float SPLASH_HOLD = 900.0f;
    private static final float SPLASH_FADE_OUT_AT = SPLASH_TYPE_START + SPLASH_TYPE_TIME + SPLASH_HOLD;
    private static final float SPLASH_FADE_OUT = 950.0f;
    private static boolean splashPlayed;
    private final AnimationUtil b;
    private final Button c;
    private final Button d;
    private final Button e;
    private final Button f;
    private final Button quitButton;
    private final List<Button> g;
    private final List<EffectMarker.a> h;
    private final long m;
    private final boolean n;

    @Compile
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        this.b.a((Interface.aM_.currentScreen instanceof MainScreen) && q());
        this.b.a(0.0f, 1.0f, 0.15f, EasingList.g, delta);
        float fMin = Math.min(1.0f, this.b.c() / 0.9f);
        double dA = MathUtil.scale(mouseX, 2);
        double dA2 = MathUtil.scale(mouseY, 2);
        ScaleUtil.a(context, 2);
        int iMethod_4486 = Interface.aM_.getWindow().getScaledWidth();
        int iMethod_4502 = Interface.aM_.getWindow().getScaledHeight();
        a(context, iMethod_4486, iMethod_4502, (int) dA, (int) dA2, 1.25f - (EasingList.s.ease(fMin) * 0.2f));
        Epsilon.h().d().i().e().a(context.getMatrices());
        a(iMethod_4486, iMethod_4502);
        a(context, iMethod_4486 * 0.5f, ((iMethod_4502 - this.c.c()) * 0.5f) - 58.0f, fMin);
        Iterator<Button> it = this.g.iterator();
        while (it.hasNext()) {
            it.next().a(context, (int) dA, (int) dA2, delta, fMin);
        }
        EffectMarker.a(context.getMatrices(), delta, this.h);
        a(context, iMethod_4486, iMethod_4502);
        ScaleUtil.a(context);
    }

    @Compile
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (p()) {
            return true;
        }
        List<EffectMarker.a> list = this.h;
        List<Button> list2 = this.g;
        double dA = MathUtil.scale(mouseX, 2);
        double dA2 = MathUtil.scale(mouseY, 2);
        EffectMarker.a(list, (float) dA, (float) dA2);
        for (Button button2 : list2) {
            if (button2.e() != null && MathUtil.a(dA, dA2, button2.f(), button2.g(), button2.b(), button2.c())) {
                button2.e().run();
                return true;
            }
        }
        return super.mouseClicked(dA, dA2, button);
    }

    @Compile
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        if (p()) {
            return true;
        }
        return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
    }

    @Compile
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        if (p()) {
            return true;
        }
        return super.mouseReleased(mouseX, mouseY, button);
    }

    static {
        NativeMethodLookup.lookup(MainScreen.class, 16);
        a = new float[2];
    }

    public MainScreen() {
        super(Text.empty());
        this.b = new AnimationUtil();
        this.h = new ArrayList();
        this.m = System.currentTimeMillis();
        this.n = false;
        splashPlayed = true;
        if (Interface.aM_.currentScreen instanceof MainScreen) {
            this.b.c(1.0f);
            this.b.d(1.0f);
            this.b.e(1.0f);
        }
        this.c = new Button(121.0f, 39.0f, "Singleplayer", () -> {
            Interface.aM_.setScreen(new SelectWorldScreen((Screen) null));
        });
        this.d = new Button(121.0f, 39.0f, "Multiplayer", () -> {
            Interface.aM_.setScreen(new MultiplayerScreen((Screen) null));
        });
        this.e = new Button(121.0f, 39.0f, "Alt Manager", () -> {
            Interface.aM_.setScreen(new AltScreen());
        });
        this.f = new Button(121.0f, 39.0f, "Settings", () -> {
            Interface.aM_.setScreen(new OptionsScreen((Screen) null, Interface.aM_.options));
        });
        this.quitButton = new Button(121.0f, 39.0f, "Quit", () -> Interface.aM_.scheduleStop());
        this.g = List.of(this.c, this.d, this.f, this.quitButton, this.e);
    }

    public MusicSound getMusic() {
        return MusicType.MENU;
    }

    public void close() {
    }

    public void renderBackground(DrawContext context, int mouseX, int mouseY, float delta) {
    }

    public static void a(DrawContext context, int width, int height, int mouseX, int mouseY, float scale) {
        float marginX = width * 0.025f;
        float marginY = height * 0.025f;
        float[] fArr = a;
        fArr[0] = fArr[0] + ((MathHelper.clamp((((mouseX / width) - 0.5f) * 2.0f) * marginX, (-marginX) * 0.9f, marginX * 0.9f) - a[0]) * 0.03f);
        float[] fArr2 = a;
        fArr2[1] = fArr2[1] + ((MathHelper.clamp((((mouseY / height) - 0.5f) * 2.0f) * marginY, (-marginY) * 0.9f, marginY * 0.9f) - a[1]) * 0.03f);
        MatrixStack matrices = context.getMatrices();
        matrices.push();
        matrices.translate(width / 2.0f, height / 2.0f, 0.0f);
        matrices.scale(scale, scale, 1.0f);
        matrices.translate((-width) / 2.0f, (-height) / 2.0f, 0.0f);
        Epsilon.h().d().i().a(matrices, Identifier.of("epsilon", "pictures/main.png"), (-marginX) + a[0], (-marginY) + a[1], width + (marginX * 2.0f), height + (marginY * 2.0f), 0.0f, -1);
        matrices.pop();
    }

    private void a(int width, int height) {
        float mainX = Math.max(28.0f, width * 0.032f);
        float mainY = height * 0.465f;
        this.c.a(mainX, mainY);
        this.d.a(mainX, mainY + 45.0f);
        this.f.a(mainX, mainY + 90.0f);
        this.quitButton.a(mainX, mainY + 135.0f);
        this.e.a(mainX + this.quitButton.b() + 5.0f, mainY + 135.0f);
    }

    private void a(DrawContext context, float centerX, float titleY, float open) {
        MatrixStack matrices = context.getMatrices();
        float scale = 0.85f + (0.15f * EasingList.s.ease(open));
        matrices.push();
        matrices.translate(centerX, titleY + 8.0f, 0.0f);
        matrices.scale(scale, scale, 1.0f);
        matrices.translate(-centerX, (-titleY) - 8.0f, 0.0f);
        int primary = Epsilon.h().d().o().a(ThemeInfo.PRIMARY).a();
        float left = Math.max(24.0f, centerX * 0.04f);
        Fonts.e.a(matrices, (Text) GradientUtil.a("Welcome to Epsilon", primary, 5.0f, 0.5f), left, titleY + 1.5f, 18.0f, 0.0f, open);
        Fonts.e.a(matrices, "Long time no see.", left + 1.0f, titleY + 22.0f, 8.0f, ColorUtil.a(255, 255, 255, (int) (160.0f * open)));
        matrices.pop();
    }

    private void a(DrawContext context, int width, int height) {
        if (!this.n) {
            return;
        }
        float elapsed = o();
        float fadeIn = MathHelper.clamp(elapsed / SPLASH_FADE_IN, 0.0f, 1.0f);
        float fadeOut = 1.0f - MathHelper.clamp((elapsed - SPLASH_FADE_OUT_AT) / SPLASH_FADE_OUT, 0.0f, 1.0f);
        float alpha = MathHelper.clamp(EasingList.y.ease(fadeIn) * EasingList.y.ease(fadeOut), 0.0f, 1.0f);
        if (alpha <= 0.001f) {
            return;
        }
        Draw2DProcessor draw = Epsilon.h().d().i();
        MatrixStack matrices = context.getMatrices();
        draw.a(matrices, 0.0f, 0.0f, width, height, 0.0f, ColorUtil.a(0, 0, 0, (int) (200.0f * alpha)));
        float size = 8.75f;
        float x = (width - Fonts.e.a(SPLASH_TEXT, size)) * 0.5f;
        float y = ((height * 0.5f) - 5.0f) + ((1.0f - EasingList.y.ease(fadeIn)) * 6.0f);
        float typed = SPLASH_TEXT.length() * MathHelper.clamp((elapsed - SPLASH_TYPE_START) / SPLASH_TYPE_TIME, 0.0f, 1.0f);
        int shown = Math.min((int) typed, SPLASH_TEXT.length());
        String head = SPLASH_TEXT.substring(0, shown);
        if (shown > 0) {
            Fonts.e.a(matrices, head, x, y, size, ColorUtil.a(255, 255, 255, (int) (255.0f * alpha)));
        }
        if (shown >= SPLASH_TEXT.length()) {
            return;
        }
        float headWidth = Fonts.e.a(head, size);
        float fraction = EasingList.p.ease(typed - shown);
        String next = String.valueOf(SPLASH_TEXT.charAt(shown));
        Fonts.e.a(matrices, next, x + headWidth, y, size, ColorUtil.a(255, 255, 255, (int) (255.0f * alpha * fraction)));
        float blink = (float) ((Math.sin(elapsed / 140.0f) + 1.0d) * 0.5d);
        draw.a(matrices, x + headWidth + (Fonts.e.a(next, size) * fraction), y - 0.5f, 0.7f, size, 0.0f, ColorUtil.a(255, 255, 255, (int) (170.0f * alpha * (0.4f + (0.6f * blink)))));
    }

    private float o() {
        return System.currentTimeMillis() - this.m;
    }

    private boolean q() {
        return !this.n || o() >= SPLASH_FADE_OUT_AT;
    }

    private boolean p() {
        return this.n && o() < SPLASH_FADE_OUT_AT;
    }
}
