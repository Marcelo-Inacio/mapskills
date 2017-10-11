/*
 * @(#)SceneListWrapper.java 1.0 28/12/2016
 *
 * Copyright (c) 2016, Fatec Jessen Vidal. All rights reserved.
 * Fatec Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */
package br.gov.sp.fatec.mapskills.restapi.wrapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.gov.sp.fatec.mapskills.domain.theme.Scene;
import br.gov.sp.fatec.mapskills.restapi.serializer.SceneListDeserializer;
import br.gov.sp.fatec.mapskills.restapi.serializer.SceneListSerializer;
/**
 * 
 * A classe {@link SceneListWrapper} encapsula uma colecao
 * de cenas de um tema de jogo para que seja serializada
 * ou deserializada.
 *
 * @author Marcelo
 * @version 1.0 28/12/2016
 */
@JsonSerialize(using = SceneListSerializer.class)
@JsonDeserialize(using = SceneListDeserializer.class)
public class SceneListWrapper {
	
	private final List<Scene> scenes = new ArrayList<>();

	public SceneListWrapper(final List<Scene> scenes) {
		this.scenes.addAll(scenes);
	}
	
	public List<Scene> getScenes() {
		return Collections.unmodifiableList(scenes);
	}

}