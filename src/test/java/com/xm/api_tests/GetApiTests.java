package com.xm.api_tests;

import com.xm.api.ExpectedData;
import com.xm.api.Requests;
import com.xm.api.dto.FilmDto;
import com.xm.api.dto.FilmsResponseDTO;
import com.xm.helpers.CommonHelper;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class GetApiTests extends BaseApiTest {

    @Test
    public void verifyUpdatingCalendarEvents() throws IOException {
        Requests requests = new Requests();

        Response getAllFilmsResponse = requests.getData(getUrl(), "films");
        Assert.assertEquals(getAllFilmsResponse.statusCode(), HttpStatus.SC_OK);
        FilmsResponseDTO filmsResponseDTO = getObjectMapper().readValue(getAllFilmsResponse.asString(), FilmsResponseDTO.class);
        List<FilmDto> filmsList = filmsResponseDTO.getResults();

        FilmDto requiredFilm = filmsList.stream()
                .filter(film -> film.getTitle().equals(ExpectedData.TITLE.getValue()))
                .findFirst()
                .orElse(null);

        assert requiredFilm != null;
        Response biggsDarklighterResponse = requiredFilm.getCharacters().stream()
                .map(character -> requests.getData(getUrl(), "people/" + CommonHelper.getNumberFromTheUrl(character)))
                .filter(responseByName -> responseByName.jsonPath().get("name").equals(ExpectedData.NAME.getValue()))
                .findFirst()
                .orElse(null);

        assert biggsDarklighterResponse != null;
        String starship = biggsDarklighterResponse.jsonPath().getString("starships[0]");

        Response responseByStarship = requests.getData(getUrl(), "starships/" + CommonHelper.getNumberFromTheUrl(starship));
        Assert.assertEquals(responseByStarship.jsonPath().getString("starship_class"), ExpectedData.STARSHIP_CLASS.getValue());

        List<String> pilots = responseByStarship.jsonPath().getList("pilots");

        boolean hasMatchingPilot = pilots.stream()
                .map(pilot -> requests.getData(getUrl(), "people/" + CommonHelper.getNumberFromTheUrl(pilot)))
                .anyMatch(responseByPilotName -> responseByPilotName.jsonPath().getString("name").equals(ExpectedData.PILOT_NAME.getValue()));

        Assert.assertTrue(hasMatchingPilot);
    }

}