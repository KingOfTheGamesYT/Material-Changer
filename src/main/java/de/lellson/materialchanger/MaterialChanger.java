package de.lellson.materialchanger;

import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;

import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.*;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.Logger;

@Mod(MaterialChanger.MODID)
public class MaterialChanger {

	public static final String MODID = "materialchanger";
	public static final String NAME = "Material Changer";
	public static final Logger LOGGER = MaterialChanger.LOGGER;

	private static final EnumMap<ModConfig.Type, ModConfig> CONFIGS = new EnumMap<>(ModConfig.Type.class);

	public MaterialChanger() {


		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onCommonSetup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onClientSetup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onInterModEnqueue);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onInterModProcess);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onLoadComplete);


		registerConfig(ModConfig.Type.COMMON, ChangerConfig.SPEC);
		CONFIGS.values().forEach(ModLoadingContext.get().getActiveContainer()::addConfig);
	}

	private void onCommonSetup(final FMLCommonSetupEvent evt) {

	//	MinecraftForge.EVENT_BUS.register(new RegisterAttributeHandler());
	//	MinecraftForge.EVENT_BUS.register(new MaterialMasterReference());
	//	MinecraftForge.EVENT_BUS.register(new DigSpeedHandler());
	}

	private void onClientSetup(final FMLClientSetupEvent evt) {

	//	MinecraftForge.EVENT_BUS.register(new ItemTooltipHandler());
	//	MinecraftForge.EVENT_BUS.register(new AttackIndicatorHandler());
	}

	private void onInterModEnqueue(final InterModEnqueueEvent evt) {

	//	InterModComms.sendTo(MaterialChanger.MODID, MaterialChanger.MODID, MaterialChanger.REGISTER_SYNC_PROVIDER,
			//	() -> null);
	}

	private void onInterModProcess(final InterModProcessEvent evt) {

		Set<InterModComms.IMCMessage> messages = InterModComms.getMessages(MaterialChanger.MODID).collect(Collectors.toSet());

		messages.stream()
		//		.filter(message -> message.getMethod().equals(MaterialChanger.REGISTER_SYNC_PROVIDER))
				.forEach(message -> {

				//	PropertyProviderUtils.registerSyncProviderMod(message.getSenderModId(), null);
				//	InterModComms.sendTo(MaterialChanger.MODID, message.getSenderModId(), MaterialChanger.RETURN_CONFIG_EVENT,
				//			() -> (Consumer<ModConfig.ConfigReloading>) PropertySyncManager.getInstance()::onModConfig);
				});

		// check for registered config provider fields
		messages.stream()
			//	.filter(message -> message.getMethod().equals(MaterialChanger.REGISTER_CONFIG_PROVIDER))
				.forEach(message -> {

				//	PropertyProviderUtils.registerSyncProviderMod(message.getSenderModId(), (ModConfig) message.getMessageSupplier().get());
				//	InterModComms.sendTo(MaterialChanger.MODID, message.getSenderModId(), MaterialChanger.RETURN_CONFIG_EVENT,
					//		() -> (Consumer<ModConfig.ConfigReloading>) ModSyncManager.getInstance()::onModConfig);
				});
	}

	private void onLoadComplete(final FMLLoadCompleteEvent evt) {

		// add listener to config events
		InterModComms.getMessages(MaterialChanger.MODID)
			//	.filter(message -> message.getMethod().equals(MaterialChanger.RETURN_CONFIG_EVENT))
				.map(message -> (Consumer<? extends Event>) message.getMessageSupplier().get())
				.forEach(FMLJavaModLoadingContext.get().getModEventBus()::addListener);

	//	ModSyncManager.getInstance().processSyncProviders();
	}

	private static void registerConfig(ModConfig.Type type, ForgeConfigSpec spec) {

		CONFIGS.put(type, new ModConfig(type, spec, ModLoadingContext.get().getActiveContainer()));
	}

}
