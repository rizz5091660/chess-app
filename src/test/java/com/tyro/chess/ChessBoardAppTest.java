package com.tyro.chess;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


public class ChessBoardAppTest {

    @Test
    public void play(){
        List<Player> players = new ArrayList<Player>();
        Player player1 = new Player(1,"W", "R", "f2");
        players.add(player1);
        Player player2 = new Player(2,"W", "N", "f6");
        players.add(player2);

        Map<Player, List<String>> mockResults = new HashMap<Player, List<String>>();
        mockResults.put(player1,getPaths(10));
        mockResults.put(player2,getPaths(8));

        ChessBoardApp chessBoardAppMock = org.mockito.Mockito.mock(ChessBoardApp.class);
        when(chessBoardAppMock.getResult()).thenReturn(mockResults);

        chessBoardAppMock.play();

        assertEquals(10, mockResults.get(player1).size());
        assertEquals(8, mockResults.get(player2).size());

    }

    private List<String> getPaths(int size){
        List<String> paths = new ArrayList<String>();
        for(int i =0; i <size; i++)
            paths.add("");
        return paths;
    }

}
