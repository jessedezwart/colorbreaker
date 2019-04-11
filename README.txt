- Het is belangrijk dat de layout van het project er zo uit ziet:

    project/
            src/
                ...packages...
                resources/
                Runner.java
            test/
                ...packages...
                TestSuite.java
            .gitlab-ci.yml
            MANIFEST.MF

- In de resources map staan alle plaatjes en de engine.
- Kijk goed hoe de plaatjes aangesproken moeten worden in de voorbeeld tiles en elementen.
- De lengte en breedte van elementen worden bepaald door de lengte en breed van de plaatjes.
- De lengte en de breedte van een tile is 80 bij 80 pixels en het is belangrijk dat het plaatje van de tile ook 80 bij 80 pixels is.