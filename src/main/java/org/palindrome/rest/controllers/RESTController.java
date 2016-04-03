package org.palindrome.rest.controllers;

import java.util.List;
import javax.servlet.http.HttpServlet;
import org.palindrome.core.GameFacade;
import org.palindrome.core.domain.Player;
import org.palindrome.rest.adapters.PalindromeResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author andrewavetisov
 */
@Controller
@RequestMapping("/")
public class RESTController extends HttpServlet {

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Player>> getBoard() {
        return new ResponseEntity<>(GameFacade.getInstance().getChampionsTable(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<PalindromeResource> addWord(@RequestBody PalindromeResource palindromeResource) {
        boolean isSuccess = GameFacade.getInstance().addWord(palindromeResource.getPlayerID(), palindromeResource.getWord());
        if (isSuccess) {
            return new ResponseEntity<>(palindromeResource, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
