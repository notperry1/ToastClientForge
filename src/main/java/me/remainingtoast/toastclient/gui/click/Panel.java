package me.remainingtoast.toastclient.gui.click;

public class Panel {
    private String label;

    public Panel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }

    public void draw(int mouseY, int mouseX, float partialTicks) {}

    public void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) {}

    public void keyTyped(final char typedChar, final int keyCode) {}

    public void mouseReleased(final int mouseX, final int mouseY, final int state) {}

    public void onGuiClosed() {}
}
