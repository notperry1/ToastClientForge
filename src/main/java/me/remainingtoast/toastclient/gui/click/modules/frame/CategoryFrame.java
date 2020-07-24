package me.remainingtoast.toastclient.gui.click.modules.frame;

import me.remainingtoast.toastclient.ToastClient;
import me.remainingtoast.toastclient.module.Module;
import me.remainingtoast.toastclient.module.ModuleManager;
import me.remainingtoast.toastclient.util.RenderUtil;
import net.minecraft.client.Minecraft;

import java.awt.*;

public class CategoryFrame extends Frame {
    private final Module.Category moduleCategory;

    private final int BORDER = 2;
    private final int TEXT_GAP = 1;
    private final ModuleManager moduleManager = ToastClient.moduleManager;

    public CategoryFrame(Module.Category moduleCategory, float x, float y, float w, float h) {
        super(moduleCategory.getLabel(), x, y, w, h);
        this.moduleCategory = moduleCategory;
    }

    @Override
    public void init() {
        super.init();
        float offsetY = getH() - 2f;
    }

    @Override
    public void moved(float x, float y) {
        super.moved(x, y);
    }

    @Override
    public void drawScreen(int x, int y, float partialTicks) {

        int offsetY = 10;
        float width = getW();

        RenderUtil.drawRect2(getX(), getY(), width, getH() + moduleManager.getModulesInCat(moduleCategory).size() * 10 + 5, 0xff050505);
        RenderUtil.drawBorderedRect2(getX() + .5f, getY() + .5f, width - 1f, getH() - 1f + moduleManager.getModulesInCat(moduleCategory).size() * 10 + 5, 0.5f, 0xff282828, 0xff282828);
        RenderUtil.drawBorderedRect2(getX() + 1.5f, getY() + 1.5f, width - 3f, getH() - 3f + moduleManager.getModulesInCat(moduleCategory).size() * 10 + 5, 0.5f, 0xff111111, 0xff3C3C3C);

        for (Module module : moduleManager.getModulesInCat(moduleCategory)) {
            mc.fontRenderer.drawStringWithShadow(module.getName(), this.getX() + 5, this.getY() + offsetY + BORDER + TEXT_GAP + 1, module.isEnabled() ? Color.GREEN.getRGB() : Color.GRAY.getRGB());
            offsetY += mc.fontRenderer.FONT_HEIGHT + TEXT_GAP;
        }
        for (float i = 2.5f; i < getW() - 2.5f; i++) {
            int color = Color.getHSBColor(i / 115, 0.9f, 1).getRGB();
            RenderUtil.drawRect2(getX() + i, getY() + 2.5f, 1, 0.5f, color);
        }
        Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(getLabel(), getX() + 5, getY() + 5, -1);
        super.drawScreen(x, y, partialTicks);
    }

    @Override
    public void mouseClicked(int x, int y, int button) {
        super.mouseClicked(x, y, button);
    }


    @Override
    public void mouseReleased(int x, int y, int button) {
        super.mouseReleased(x, y, button);
        final boolean hovered = RenderUtil.isHovered((int) getX(), (int) getY(), (int) getW(), (int) getH(), x, y);
        final boolean insideComponent = x >= this.getX() + 5 && y >= this.getY() + 10 + BORDER + TEXT_GAP + 1;
        if (hovered && button == 0) {
            for (Module module : moduleManager.getModulesInCat(moduleCategory)) {
                if (insideComponent) {
                    module.toggle();
                    this.setDragging(false);
                }
            }
        }
    }

    @Override
    public void keyTyped(char character, int key) {
        super.keyTyped(character, key);
    }
}
