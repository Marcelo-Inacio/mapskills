/*
 * @(#)GameThemeController.java 1.0 1 02/09/2017
 *
 * Copyright (c) 2017, Fatec-Jessen Vidal. All rights reserved.
 * Fatec-Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */

package br.gov.sp.fatec.mapskills.restapi;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.gov.sp.fatec.mapskills.application.GameThemeApplicationServices;
import br.gov.sp.fatec.mapskills.domain.theme.GameTheme;
import br.gov.sp.fatec.mapskills.domain.theme.Scene;
import br.gov.sp.fatec.mapskills.restapi.wrapper.GameThemeWrapper;
import br.gov.sp.fatec.mapskills.restapi.wrapper.ListWrapper;
import br.gov.sp.fatec.mapskills.restapi.wrapper.SceneListWrapper;
import br.gov.sp.fatec.mapskills.restapi.wrapper.SceneWrapper;
import lombok.AllArgsConstructor;

/**
 * A classe {@link GameThemeController}
 *
 * @author Marcelo
 * @version 1.0 02/09/2017
 */
@RestController
@AllArgsConstructor
public class GameThemeController {
	
	private final GameThemeApplicationServices applicationServices;
	
	/**
	 * 
	 * Expoe endpoint para que seja realizado o cadastro de um novo tema na aplicacao.
	 */
	@PostMapping("/game/theme")
	public void createTheme(@RequestBody final GameThemeWrapper themeWrapper) {
		applicationServices.createGameTheme(themeWrapper.getGameTheme());
	}
	
	/***
	 * 
	 * Expoe endpoint para que retorne todos temas cadastrados na aplicacao.
	 */
	@GetMapping("/game/themes")
	public ListWrapper<GameTheme> getThemes(
			@RequestParam(value = "onlyActives", required = false) final boolean onlyActives) {
		final List<GameTheme> themes = applicationServices.getAllGameThemes(onlyActives);
		return new ListWrapper<>(themes);
	}
	
	/**
	 * 
	 * Expoe endpoint para que seja criada uma nova cena para um tema do jogo na aplicacao.
	 */
	@PostMapping("/game/{id}/scene")
	public void createScene(@PathVariable("id") final Long gameThemeId,
			@RequestBody final SceneWrapper sceneWrapper) {
		applicationServices.createScene(gameThemeId, sceneWrapper);
	}
	
	/**
	 * 
	 * Expoe endpoint para que retorne todas as cenas de um tema de jogo contido na aplicacao.
	 */
	@GetMapping("/game/theme/{themeId}")
	public ListWrapper<Scene> getAllScenesByThemeId(@PathVariable("themeId") final Long themeId) {
		return new ListWrapper<>(applicationServices.getAllScenesByThemeId(themeId));
	}
	
	/**
	 * 
	 * Expoe endpoint para excluir uma questao de cena.
	 */
	@DeleteMapping("/game/{themeId}/scene/{sceneId}/question")
	public void deleteQuestion(@PathVariable("themeId") final Long themeId,
			@PathVariable("sceneId") final Long sceneId) {
		applicationServices.deleteQuestion(themeId, sceneId);
	}
	
	/**
	 * 
	 * Expoe endpoint para remocao de uma cena.
	 */
	@DeleteMapping("/game/{themeId}/scene/{sceneId}")
	public void deleteScene(@PathVariable("themeId") final Long themeId,
			@PathVariable("sceneId") final Long sceneId) {
		applicationServices.deleteScene(themeId, sceneId);
	}
	
	/**
	 * 
	 * Expoe endpoint para atualizacao de um cena de um tema de jogo na aplicacao.
	 */
	@PutMapping("/game/{themeId}/scene/{sceneId}")
	public void updateScene(@PathVariable("themeId") final Long themeId,
			@PathVariable("sceneId") final Long sceneId,
			@RequestBody final SceneWrapper sceneWrapper) {
		applicationServices.updateScene(themeId, sceneId, sceneWrapper);
	}
	
	/**
	 * 
	 * Expoe endpoint para realiza atualizacao das ordens das cenas de um tema.
	 */
	@PutMapping("/game/{themeId}/scenes")
	public void updateIndexScenes(@PathVariable("themeId") final Long themeId,
			@RequestBody final SceneListWrapper sceneListWrapper) {
		applicationServices.updateSceneIndexes(themeId, sceneListWrapper.getScenes());
	}
	
	/**
	 * 
	 * Expoe endpoint para atualizar o <i>status</i> de um tema de jogo.
	 */
	@PutMapping("/game/{themeId}")
	public void updateGameThemeStatus(@PathVariable("themeId") final Long themeId,
			@RequestParam(name = "status", required = true) final boolean status) {
		applicationServices.updateGameThemeStatus(themeId, status);
	}
}