package org.palindorme.rest.controllers;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.palindrome.rest.controllers.RESTController;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import org.junit.Test;
import static org.hamcrest.Matchers.*;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.palindrome.core.GameFacade;
import org.palindrome.core.domain.Player;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

/**
 *
 * @author andrewavetisov
 */
public class RESTControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private RESTController controller;

    @Mock
    private GameFacade game;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        // create mock for mvc
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void getBoardTest() throws Exception {

        List<Player> players = new ArrayList<>();
        players.add(new Player(111));
        players.add(new Player(222));
        players.add(new Player(333));

        players.get(0).addPalindrome("wow");
        players.get(1).addPalindrome("wowowow");
        players.get(2).addPalindrome("wowowowow");

        when(game.getChampionsTable()).thenReturn(players);

        mockMvc.perform(
                get("/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.*", hasSize(players.size())))
                .andExpect(jsonPath("$.[0].id").value(players.get(0).getId()))
                .andExpect(jsonPath("$.[1].id").value(players.get(1).getId()))
                .andExpect(jsonPath("$.[2].id").value(players.get(2).getId()))
                .andExpect(jsonPath("$.[0].score").value(players.get(0).getScore()))
                .andExpect(jsonPath("$.[1].score").value(players.get(1).getScore()))
                .andExpect(jsonPath("$.[2].score").value(players.get(2).getScore()))
                .andExpect(status().isOk()) //.andDo(print())
                ;
    }

    @Test
    public void addWordTest() throws Exception {

        mockMvc.perform(
                post("/")
                .content("{}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        when(game.addWord(1, "wow")).thenReturn(true);
        mockMvc.perform(
                post("/")
                .content("{\"playerID\":\"1\", \"word\":\"wow\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                //.andDo(print())
                ;
    }
}
