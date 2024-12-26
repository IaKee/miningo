package com.miningo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.miningo.task.EventTask;
import com.miningo.task.ItemTask;
import com.miningo.task.TaskManager;
import net.fabricmc.api.ModInitializer;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Items;
import net.minecraft.stat.Stats;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MiningoMod implements ModInitializer {
	public static final String MOD_ID = "miningo";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public MiningoSettings config;

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing Miningo!");

		// updates all mod settings
		//loadConfig();

		// TODO: test event, delete this
		ChaosEventManager.initialize();


		TaskManager taskManager = new TaskManager();

// Adicione tarefas
		taskManager.addTask(new ItemTask("Ordenhar uma vaca",
				"Obtenha leite de uma vaca usando um balde.",
				Items.MILK_BUCKET.getDefaultStack(),
				1));

		taskManager.addTask(new ItemTask("Pack de pedra lisa",
				"Obtenha 64 blocos de pedra lisa.",
				Items.SMOOTH_STONE.getDefaultStack(),
				64));

		taskManager.addTask(new EventTask(
				"Mate um Creeper",
				"Derrote um Creeper pela primeira vez.",
				EntityType.CREEPER
				));

		//taskManager.addTask(new EventTask(
		//		"Troque com um Villager",
		//		"Realize uma troca com um Villager pela primeira vez.",
		//		player -> player.getStatHandler().getStat(Stats.TRADED_WITH_VILLAGER) > 0 // Verifica estatísticas
		//));

		TaskManager.initialize();
	}

	public void loadConfig(){
		File configFile = new File("./config/miningo/miningo.json");
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		if(configFile.exists()){
			try (FileReader reader = new FileReader(configFile)) {
				MiningoSettings newSettings = gson.fromJson(reader, MiningoSettings.class);
				reader.close();
			} catch (IOException e) {
				LOGGER.error("Could not load Miningo settings:" + e.getMessage());
			}
		} else {
			// if settings file does not exist, creates a new one with default values
			config = new MiningoSettings();
			saveConfig();
		}
	}

	public void saveConfig() {
		Gson gson = new Gson();
		File file = new File("./config/miningo/miningo.json");

		if(!file.getParentFile().exists()) {
			// if parent directories does not exist
			// creates them
			boolean ok = file.getParentFile().mkdirs();
			if(!ok){
				LOGGER.error("Could not create folders for Miningo configuration!");
				return;
			}
		}

		try {
			FileWriter writer = new FileWriter(file);
			writer.write(gson.toJson(config));
			writer.close();
		} catch (IOException e) {
			LOGGER.error("Could not save Miningo settings: " + e.getMessage());
		}
	}
}