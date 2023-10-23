package de.vepexlegit.playeresp;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;

@Mod(modid = "playeresp", version = "1.0")
public class PlayerESP {
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onRenderWorldLast(RenderWorldLastEvent event) {
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        for (EntityPlayer player : Minecraft.getMinecraft().theWorld.playerEntities) {
            if (player == Minecraft.getMinecraft().thePlayer) {
                continue;
            }
            double x = player.posX - Minecraft.getMinecraft().getRenderManager().viewerPosX;
            double y = player.posY - Minecraft.getMinecraft().getRenderManager().viewerPosY;
            double z = player.posZ - Minecraft.getMinecraft().getRenderManager().viewerPosZ;
            GL11.glColor4f(1.0F, 0.5F, 0.0F, 1.0F);
            GL11.glBegin(GL11.GL_LINE_LOOP);
            GL11.glVertex3d(x - 0.5, y, z - 0.5);
            GL11.glVertex3d(x - 0.5, y + 1.8, z - 0.5);
            GL11.glVertex3d(x + 0.5, y + 1.8, z - 0.5);
            GL11.glVertex3d(x + 0.5, y, z - 0.5);
            GL11.glEnd();
            GL11.glBegin(GL11.GL_LINE_LOOP);
            GL11.glVertex3d(x - 0.5, y, z + 0.5);
            GL11.glVertex3d(x - 0.5, y + 1.8, z + 0.5);
            GL11.glVertex3d(x + 0.5, y + 1.8, z + 0.5);
            GL11.glVertex3d(x + 0.5, y, z + 0.5);
            GL11.glEnd();
            GL11.glBegin(GL11.GL_LINES);
            GL11.glVertex3d(x - 0.5, y, z - 0.5);
            GL11.glVertex3d(x - 0.5, y, z + 0.5);
            GL11.glVertex3d(x - 0.5, y + 1.8, z - 0.5);
            GL11.glVertex3d(x - 0.5, y + 1.8, z + 0.5);
            GL11.glVertex3d(x + 0.5, y + 1.8, z - 0.5);
            GL11.glVertex3d(x + 0.5, y + 1.8, z + 0.5);
            GL11.glVertex3d(x + 0.5, y, z - 0.5);
            GL11.glVertex3d(x + 0.5, y, z + 0.5);
            GL11.glEnd();
        }
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
    }
}
