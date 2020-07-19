package me.remainingtoast.toastclient.gui.rewrite.click;

import me.remainingtoast.toastclient.gui.rewrite.click.panel.Panel;
import me.remainingtoast.toastclient.gui.rewrite.click.panel.panels.ModulesPanel;
import me.remainingtoast.toastclient.util.RenderUtil;
import me.remainingtoast.toastclient.util.font.CFontRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NewToastGui extends GuiScreen {

    public List<Panel> panels = new ArrayList<>();
    public Panel currentPanel;
    public static CFontRenderer font = new CFontRenderer(new Font("Calibri", 0, 20), true, true);

    public void init(){
        panels.clear();
        GL11.glColor4f(1, 1, 1, 1);
        panels.add(currentPanel = new ModulesPanel());
//        panels.add(new FriendsPanel());
//        panels.add(new ConfigPanel());
//        panels.add(new SettingsPanel());
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        ScaledResolution res = new ScaledResolution(mc);
        int x = 0;
        for (Panel panel : panels) {
            float renderX = (res.getScaledWidth() / 2) + x - (panels.size() * 20);
//            RenderUtil.drawRect(renderX - 5f, 0, renderX + font.getStringWidth(panel.getLabel()) + 5f, font.getHeight() + 6f, new Color(0, 0, 0, panel == currentPanel ? 170 : 137).getRGB());
//            font.drawStringWithShadow(panel.getLabel(), renderX, 4, -1);
            x += font.getStringWidth(panel.getLabel()) + 10;
        }
        currentPanel.draw(mouseX, mouseY, partialTicks);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        ScaledResolution res = new ScaledResolution(mc);
        int x = 0;
        for (Panel panel : panels) {
            float renderX = (res.getScaledWidth() / 2) + x - (panels.size() * 20);
            if (RenderUtil.withinBounds(mouseX, mouseY, renderX - 4f, 0, renderX + font.getStringWidth(panel.getLabel()) + 5f, font.getHeight() + 6f)) {
                currentPanel = panel;
            }
            x += font.getStringWidth(panel.getLabel()) + 10;
        }
        currentPanel.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int mouseButton) {
        super.mouseReleased(mouseX, mouseY, mouseButton);
        currentPanel.mouseReleased(mouseX, mouseY, mouseButton);
    }

    @Override
    public void keyTyped(char typedChar, int key) {
        if (key == Keyboard.KEY_ESCAPE) {
            Minecraft.getMinecraft().player.closeScreen();
        }
        currentPanel.keyTyped(typedChar, key);
    }

    @Override
    public void onGuiClosed() {
        currentPanel.onGuiClosed();
    }


    @Override
    public boolean doesGuiPauseGame()
    {
        return false;
    }
}
