package de.lellson.materialchanger;


import com.google.common.collect.Lists;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.List;

@SuppressWarnings({"WeakerAccess", "SameParameterValue"})
public class ChangerConfig {

	private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();



	public static final ForgeConfigSpec.ConfigValue<List<String>> ATTACK_DAMAGE;
	public static final ForgeConfigSpec.ConfigValue<List<String>> ATTACK_SPEED;
	public static final ForgeConfigSpec.ConfigValue<List<String>> ARMOR_PROTECTION;
	public static final ForgeConfigSpec.ConfigValue<List<String>> ARMOR_TOUGHNESS;
	public static final ForgeConfigSpec.ConfigValue<List<String>> STACK_SIZE;
	public static final ForgeConfigSpec.ConfigValue<List<String>> DURABILITY;
	public static final ForgeConfigSpec.ConfigValue<List<String>> EFFICIENCY;
	public static final ForgeConfigSpec.ConfigValue<List<String>> HARVEST_LEVEL;
	public static final ForgeConfigSpec.ConfigValue<List<String>> ENCHANTABILITY;

	static {



		BUILDER.comment("This section allows you to change attributes of items. Each change takes 1 line with the following format:<namespace>:<path>, Be sure to install Attribute Fix if you didn't notice any changes (This usually means you put an attribute higher than vanilla allows) The property you want to change. Allowed properties: ARMOR_PROTECTION, ARMOR_TOUGHNESS, ATTACK_DAMAGE, ATTACK_SPEED, DURABILITY, EFFICIENCY, ENCHANTABILITY, HARVEST_LEVEL, STACKSIZE\n" +
				" item: The id of the item. This is usually the mod id (or \"minecraft\" for vanilla) followed by a colon followed by the item name. e.g. minecraft:iron_sword\n" +
				" value: The new value for the property.\n" +
				" " +
				" Keep in mind that changing the enchantability or harvest level affects the whole tool material, which means that you technically only need to change it for one tool.\n" +
				" Unfortunately this also means that changing those attributes for one item also applies the change to every item with the same tool material (example below).\n" +
				" Also, you can't change the attack speed of swords at the moment. Sorry!");

		BUILDER.push("attribute_item_stats");
		ATTACK_DAMAGE = ChangerConfig.BUILDER.comment(createDescription("Amount of melee damage dealt when fighting.", 0.0, 2048.0)).define("Attack Damage List", Lists.newArrayList());
		ATTACK_SPEED = ChangerConfig.BUILDER.comment(createDescription("Speed at which the attack cooldown recharges, higher values make it recharge faster.", 0.0, 1024.0)).define("Attack Speed List", Lists.newArrayList());
		ARMOR_PROTECTION = ChangerConfig.BUILDER.comment(createDescription("Amount of armor protection.", 0.0, 30.0)).define("Armor List", Lists.newArrayList());
		ARMOR_TOUGHNESS = ChangerConfig.BUILDER.comment(createDescription("Amount of armor toughness.", 0.0, 20.0)).define("Armor Toughness List", Lists.newArrayList());
		STACK_SIZE = ChangerConfig.BUILDER.comment(createDescription("Specify max stack size for any item.", 0.0, 64.0, "Must not have durability")).define("Stack Size List", Lists.newArrayList());
		DURABILITY = ChangerConfig.BUILDER.comment(createDescription("Change durability for any damageable item.\nSetting to 0 will make the item unbreakable.", "Must have durability")).define("Durability List", Lists.newArrayList());
		EFFICIENCY = ChangerConfig.BUILDER.comment(createDescription("Change dig speed value for any item.\nSetting to 0 will prevent the item from mining anything.")).define("Dig Speed List", Lists.newArrayList());
		HARVEST_LEVEL = ChangerConfig.BUILDER.comment(createDescription("Change harvest level value for any tool item.\nSetting to -1 will remove any harvest level present.", "Must be a tool")).define("Harvest Level List", Lists.newArrayList());
		ENCHANTABILITY = ChangerConfig.BUILDER.comment(createDescription("Change enchantability value for any enchantable item.\nSetting to 0 will make enchanting this item impossible.", "Must be enchantable")).define("Enchantability List", Lists.newArrayList());
		BUILDER.pop();
	}

	public static final ForgeConfigSpec SPEC = BUILDER.build();

	private static String createDescription(String text) {

		return createDescription(text, 0.0, Integer.MAX_VALUE);
	}

	private static String createDescription(String text, String condition) {

		return createDescription(text, 0.0, Integer.MAX_VALUE, condition);
	}

	private static String createDescription(String text, double min, double max) {

		return text + "\nActual Range: " + min + " ~ " + max;
	}

	private static String createDescription(String text, double min, double max, String condition) {

		return createDescription(text, min, max) + "\nCondition: " + condition;
	}

}