# gra - Golden Raspberry Awards
- API RESTful que possibilitar a leitura da lista de indicados e vencedores da categoria Pior Filme do Golden Raspberry Awards.

# Stack
- Linux Mint 20.3 Cinnamon
- Java 11.0.16
- Spring Boot 2.7.4
- Spring Boot DevTools
- Lombok
- H2 Database
- JPA
- JUnit
- Maven

# Execução
- mvn spring-boot:run

# Funcionalidades
- Leitura de csv e importação para base H2
- Todas as informações do csv importatado na base, formato em JSon
- Produtores com maior e menor intervalo entre dois prêmios consecutivos
- Todos os filmes ganhadores por ordem decrescente

# EndPoints
1. Leitura de csv e importação para base H2
- JDBC URL: jdbc:h2:mem:gradb
- User Name: sa
- Password: (SEM PASSWORD)
- URL: /h2-console/ (Ex.: http://localhost:8090/h2-console/)

2. Funcionalidade: Todas as informações do csv importatado na base, formato em JSon
- Method: GET
- URL: /v1/movies (Ex.: http://localhost:8090/v1/movies)

        [
            {
                "id": 1,
                "movieYear": 1980,
                "title": "Can't Stop the Music",
                "isWinner": true,
                "producers": [
                    {
                        "id": 1,
                        "producerName": "Allan Carr"
                    }
                ],
                "studios": [
                    {
                        "id": 1,
                        "studioName": "Associated Film Distribution"
                    }
                ]
            }
        ]

3. Funcionalidade: Produtores com maior e menor intervalo entre dois prêmios consecutivos
- Method: GET
- URL: /v1/movies/interval (Ex.: http://localhost:8090/v1/movies/interval)

        {
            "min": [
                {
                    "producerName": "Joel Silver",
                    "interval": 1991,
                    "previousWin": 1990,
                    "followingWin": 1
                },
                {
                    "producerName": "Bo Derek",
                    "interval": 1990,
                    "previousWin": 1984,
                    "followingWin": 6
                }
            ],
            "max": [
                {
                    "producerName": "Buzz Feitshans",
                    "interval": 1994,
                    "previousWin": 1985,
                    "followingWin": 9
                },
                {
                    "producerName": "Matthew Vaughn",
                    "interval": 2015,
                    "previousWin": 2002,
                    "followingWin": 13
                }
            ]
        }

4. Todos os filmes ganhadores por ordem decrescente
- Method: GET
- URL: /v1/movies/winners (Ex.: http://localhost:8090/v1/movies/winners)

        [
            {
                "id": 1,
                "movieYear": 1980,
                "title": "Can't Stop the Music",
                "isWinner": true,
                "producers": [
                    {
                        "id": 1,
                        "producerName": "Allan Carr"
                    }
                ],
                "studios": [
                    {
                        "id": 1,
                        "studioName": "Associated Film Distribution"
                    }
                ]
            }
        ]