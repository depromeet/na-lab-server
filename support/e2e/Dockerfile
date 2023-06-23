FROM ghcr.io/orange-opensource/hurl:3.0.1

COPY *.hurl hurls/

#ENTRYPOINT ["hurl", "--very-verbose", "--color", "--test", "hurls/1_survey_create.hurl" ]
ENTRYPOINT hurl --very-verbose --color --test hurls/*.hurl
#CMD hurl --very-verbose --color --test hurls/1_survey_create.hurl
