package aethereal.ui.screen;

import aethereal.render.ScaleUtil;
import aethereal.core.NativeMethodLookup;
import static aethereal.core.Interface.aM_;
import aethereal.core.Epsilon;
import aethereal.core.Interface;
import aethereal.config.ThemeInfo;
import aethereal.config.ThemeType;
import aethereal.core.Category;
import aethereal.core.Module;
import aethereal.render.EasingList;
import aethereal.render.Fonts;
import aethereal.render.ColorUtil;
import aethereal.util.MathUtil;

import aethereal.ui.screen.GUIPanel;

import aethereal.render.AnimationUtil;
import aethereal.ui.element.TextField;
import aethereal.api.Compile;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import lombok.Generated;
import net.minecraft.text.Text;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.Identifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector2f;
import org.joml.Vector4f;

public class GUIScreen extends Screen {
    private final TextField a;
    private final AnimationUtil b;
    private final List<GUIPanel> c;
    private final AnimationUtil themeMenuAnimation = new AnimationUtil();
    private String d;
    private boolean themeMenuOpen;
    private static final int[] ACCENT_COLORS = {0xFF9A86F7, 0xFF67D6D0, 0xFF72D394, 0xFFFFB55F, 0xFFF07BA7};

    @Compile
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        double dA = MathUtil.scale(mouseX, 2);
        double dA2 = MathUtil.scale(mouseY, 2);
        ScaleUtil.a(context, 2);
        double dSum = this.c.stream().mapToDouble(new ToDoubleFunction() {
            @Override
            public double applyAsDouble(Object obj) {
                return ((GUIPanel) obj).f().z;
            }
        }).sum();
        float size = (this.c.size() - 1) * 8.0f;
        MinecraftClient class_310Var = Interface.aM_;
        int iMethod_4486 = class_310Var.getWindow().getScaledWidth();
        float f = size + ((float) dSum);
        float f2 = (iMethod_4486 - f) * 0.5f;
        float f3 = f2;
        float f4 = 0.0f;
        for (final GUIPanel gUIPanel : this.c) {
            Vector4f vector4fF = gUIPanel.f();
            gUIPanel.a(Epsilon.h().d().t().e().stream().filter(obj -> this.a(gUIPanel, (Module) obj)).sorted(Comparator.comparing(new Function() {
                @Override
                public Object apply(Object obj) {
                    return ((Module) obj).j();
                }
            }, String.CASE_INSENSITIVE_ORDER)).toList());
            vector4fF.x = f3;
            vector4fF.y = (class_310Var.getWindow().getScaledHeight() - vector4fF.w) * 0.5f;
            f4 = vector4fF.y;
            gUIPanel.a(context, (int) dA, (int) dA2, delta);
            f3 += vector4fF.z + 8.0f;
        }
        Iterator<GUIPanel> it = this.c.iterator();
        while (it.hasNext()) {
            it.next().a(context, dA, dA2, delta);
        }
        float f5 = ((GUIPanel) this.c.getFirst()).f().w;
        MatrixStack class_4587VarMethod_51448 = context.getMatrices();
        float fC = ((GUIPanel) this.c.getFirst()).b().c();
        float fEase = EasingList.s.ease(fC);
        class_4587VarMethod_51448.push();
        float fEase2 = EasingList.p.ease(fC);
        float f6 = (0.5f * f) + f2;
        float f7 = f5 + f4;
        float f8 = 12.0f + f7 + 10.0f;
        float f9 = ((1.0f - fEase2) * 14.0f) + f8;
        float f10 = (0.15f * fEase) + 0.85f;
        class_4587VarMethod_51448.translate(f6, f9, 0.0f);
        class_4587VarMethod_51448.scale(f10, f10, 1.0f);
        class_4587VarMethod_51448.translate(-f6, -f8, 0.0f);
        a(context, f6, f7, (int) dA, (int) dA2, delta);
        class_4587VarMethod_51448.pop();
        a(context.getMatrices(), f6, f4, delta);
        this.themeMenuAnimation.a(0.0f, 1.0f, 0.2f, EasingList.p, delta);
        this.themeMenuAnimation.a(this.themeMenuOpen);
        drawBrandMenu(context, (int) dA, (int) dA2, EasingList.p.ease(this.themeMenuAnimation.c()));
        ScaleUtil.a(context);
    }

    private void drawBrandMenu(DrawContext context, int mouseX, int mouseY, float open) {
        var draw = Epsilon.h().d().i();
        var theme = Epsilon.h().d().o();
        var matrices = context.getMatrices();
        int accent = theme.a(ThemeInfo.PRIMARY).a();
        boolean hovered = MathUtil.a(mouseX, mouseY, 8, 8, 22, 22);
        draw.a(matrices, 7, 7, 24, 24, 7, ColorUtil.a(theme.a(ThemeInfo.BACKGROUND_GUI).a(), 235));
        draw.a(matrices, 7, 7, 24, 24, 7, 0.5f, ColorUtil.a(accent, hovered ? 180 : 90));
        draw.a(matrices, Identifier.of("epsilon", "pictures/logo"), 10, 10, 18, 18, 0, -1);
        if (open <= 0.001f) return;

        float x = 8;
        float y = 36;
        float scale = 0.92f + (0.08f * open);
        matrices.push();
        matrices.translate(x, y, 0);
        matrices.scale(scale, scale, 1.0f);
        matrices.translate(-x, -y, 0);
        draw.a(matrices, x, y, 142, 93, 8, ColorUtil.a(theme.a(ThemeInfo.BACKGROUND_GUI).a(), (int) (245 * open)));
        draw.a(matrices, x, y, 142, 93, 8, 0.5f, ColorUtil.a(accent, (int) (95 * open)));
        Fonts.c.a(matrices, "Appearance", x + 10, y + 12, 8, ColorUtil.a(theme.a(ThemeInfo.TEXT).a(), (int) (255 * open)));
        float buttonY = y + 22;
        boolean light = theme.a() == ThemeType.LIGHT;
        draw.a(matrices, x + 9, buttonY, 58, 19, 5, ColorUtil.a(accent, light ? 115 : 45));
        draw.a(matrices, x + 75, buttonY, 58, 19, 5, ColorUtil.a(accent, light ? 45 : 115));
        Fonts.c.a(matrices, "Light", x + 22, buttonY + 6, 6.5f, ColorUtil.a(theme.a(ThemeInfo.TEXT).a(), (int) (255 * open)));
        Fonts.c.a(matrices, "Dark", x + 93, buttonY + 6, 6.5f, ColorUtil.a(theme.a(ThemeInfo.TEXT).a(), (int) (255 * open)));
        for (int i = 0; i < ACCENT_COLORS.length; i++) {
            float swatchX = x + 10 + (i * 24);
            draw.a(matrices, swatchX, y + 55, 17, 17, 5, ColorUtil.a(ACCENT_COLORS[i], (int) (255 * open)));
            if (Math.abs(theme.a(ThemeInfo.PRIMARY).a() - ACCENT_COLORS[i]) < 0x00101010) {
                draw.a(matrices, swatchX - 1, y + 54, 19, 19, 5, 0.7f, ColorUtil.a(theme.a(ThemeInfo.TEXT).a(), 210));
            }
        }
        matrices.pop();
    }

    @Compile
    public boolean mouseClicked(final double mouseX, final double mouseY, final int button) {
        TextField textField = this.a;
        List<GUIPanel> list = this.c;
        double scaledX = MathUtil.scale(mouseX, 2);
        double scaledY = MathUtil.scale(mouseY, 2);
        if (button == 0 && MathUtil.a(scaledX, scaledY, 7, 7, 24, 24)) {
            this.themeMenuOpen = !this.themeMenuOpen;
            return true;
        }
        if (button == 0 && this.themeMenuOpen) {
            if (MathUtil.a(scaledX, scaledY, 17, 58, 58, 19)) {
                Epsilon.h().d().o().setType(ThemeType.LIGHT);
                return true;
            }
            if (MathUtil.a(scaledX, scaledY, 83, 58, 58, 19)) {
                Epsilon.h().d().o().setType(ThemeType.DARK);
                return true;
            }
            for (int i = 0; i < ACCENT_COLORS.length; i++) {
                if (MathUtil.a(scaledX, scaledY, 18 + (i * 24), 90, 17, 17)) {
                    Epsilon.h().d().o().a(ThemeInfo.PRIMARY).a(ACCENT_COLORS[i]);
                    return true;
                }
            }
        }
        textField.a(scaledX, scaledY, button);
        if (list.stream().filter(obj -> GUIScreen.f((GUIPanel) obj)).anyMatch(obj -> ((GUIPanel) obj).a(MathUtil.scale(mouseX, 2), MathUtil.scale(mouseY, 2), button))) {
            return true;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Compile
    public boolean mouseReleased(final double mouseX, final double mouseY, final int button) {
        if (this.c.stream().filter(obj -> GUIScreen.e((GUIPanel) obj)).anyMatch(obj -> ((GUIPanel) obj).b(MathUtil.scale(mouseX, 2), MathUtil.scale(mouseY, 2), button))) {
            return true;
        }
        return super.mouseReleased(mouseX, mouseY, button);
    }

    @Compile
    public boolean mouseDragged(final double mouseX, final double mouseY, final int button, final double deltaX, final double deltaY) {
        TextField textField = this.a;
        List<GUIPanel> list = this.c;
        textField.b(MathUtil.scale(mouseX, 2), MathUtil.scale(mouseY, 2), button);
        if (list.stream().filter(obj -> GUIScreen.d((GUIPanel) obj)).anyMatch(obj -> ((GUIPanel) obj).a(MathUtil.scale(mouseX, 2), MathUtil.scale(mouseY, 2), button, MathUtil.scale(deltaX, 2), MathUtil.scale(deltaY, 2)))) {
            return true;
        }
        return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
    }

    @Compile
    public boolean mouseScrolled(final double mouseX, final double mouseY, double horizontalAmount, final double verticalAmount) {
        double scaledX = MathUtil.scale(mouseX, 2);
        double scaledY = MathUtil.scale(mouseY, 2);
        for (GUIPanel panel : this.c) {
            if (panel.d() == null) {
                continue;
            }
            Vector4f bounds = panel.f();
            if (MathUtil.a(scaledX, scaledY, bounds.x, bounds.y, bounds.z, bounds.w)) {
                return panel.a(scaledX, scaledY, verticalAmount);
            }
        }
        return super.mouseScrolled(mouseX, mouseY, horizontalAmount, verticalAmount);
    }

    @Compile
    public boolean keyPressed(final int keyCode, final int scanCode, final int modifiers) {
        TextField textField = this.a;
        List<GUIPanel> list = this.c;
        if (keyCode == 70 && (modifiers & 2) != 0) {
            textField.a(!textField.j());
            return true;
        }
        if (textField.j()) {
            textField.a(keyCode, scanCode, modifiers);
            return true;
        }
        if (list.stream().filter(obj -> GUIScreen.b((GUIPanel) obj)).anyMatch(obj -> ((GUIPanel) obj).a(keyCode, scanCode, modifiers))) {
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Compile
    public boolean charTyped(final char character, final int modifiers) {
        TextField textField = this.a;
        List<GUIPanel> list = this.c;
        if (textField.j()) {
            textField.a(character, modifiers);
            return true;
        }
        if (list.stream().filter(obj -> GUIScreen.a((GUIPanel) obj)).anyMatch(obj -> ((GUIPanel) obj).a(character, modifiers))) {
            return true;
        }
        return super.charTyped(character, modifiers);
    }

    static {
        NativeMethodLookup.lookup(GUIScreen.class, 5);
    }

    @Generated
    public TextField a() {
        return this.a;
    }

    @Generated
    public AnimationUtil b() {
        return this.b;
    }

    @Generated
    public List<GUIPanel> c() {
        return this.c;
    }

    @Generated
    public String d() {
        return this.d;
    }

    public GUIScreen(Text title) {
        super(title);
        this.a = new TextField(TextField.a.GUI);
        this.b = new AnimationUtil();
        this.c = new ArrayList();
        for (Category category : Category.values()) {
            this.c.add(new GUIPanel(category));
        }
        this.a.a("Поиск по модулям");
    }

    public void close() {
        super.close();
        this.c.forEach(panel -> {
            panel.b().c(0.0f);
        });
    }

    public void renderBackground(DrawContext context, int mouseX, int mouseY, float delta) {
        Epsilon.h().d().i().e().a(context.getMatrices());
        context.fill(0, 0, this.width, this.height, ColorUtil.a(0, 0, 0, 110));
    }

    public boolean a(GUIPanel panel, Module module) {
        return module.l() == panel.c() && module.j().toLowerCase().contains(this.a.g().toString().toLowerCase());
    }

    private void a(DrawContext context, float centerX, float panelBottom, int mouseX, int mouseY, float delta) {
        this.a.b(new Vector2f(100.0f, 20.0f));
        this.a.a(new Vector2f(centerX - 50.0f, panelBottom + 12.0f));
        this.a.a(context, mouseX, mouseY, delta, 1.0f);
    }

    private void a(MatrixStack matrices, float centerX, float panelTop, float delta) {
        Epsilon.h().d().o();
        Module hovered = (Module) this.c.stream().map((v0) -> {
            return v0.e();
        }).filter(module -> {
            return (module == null || module.k() == null || module.k().isEmpty()) ? false : true;
        }).findFirst().orElse(null);
        if (hovered != null && !hovered.k().equals(this.d)) {
            this.d = hovered.k();
            this.b.c(0.0f);
        }
        this.b.a(0.0f, 1.0f, 0.3f, EasingList.i, delta);
        this.b.a(hovered != null);
        float fade = EasingList.p.ease(this.b.c());
        if (fade > 0.0f && this.d != null) {
            float x = centerX - (Fonts.c.a(this.d, 10.0f) / 2.0f);
            float y = ((panelTop - Fonts.c.a(10.0f)) - 8.0f) + ((1.0f - fade) * 4.0f);
            Fonts.c.a(matrices, this.d, x + 0.5f, y + 0.5f, 10.0f, ColorUtil.a(ColorUtil.a(0, 0, 0, 255), 0.5f * fade));
            Fonts.c.a(matrices, this.d, x, y, 10.0f, ColorUtil.a(ColorUtil.a(255, 255, 255, 255), fade));
        }
    }

    public static boolean f(GUIPanel panel) {
        return panel.d() != null;
    }

    public static boolean e(GUIPanel panel) {
        return panel.d() != null;
    }

    public static boolean d(GUIPanel panel) {
        return panel.d() != null;
    }

    public static boolean c(GUIPanel panel) {
        return panel.d() != null;
    }

    public static boolean b(GUIPanel panel) {
        return panel.d() != null;
    }

    public static boolean a(GUIPanel panel) {
        return panel.d() != null;
    }
}
