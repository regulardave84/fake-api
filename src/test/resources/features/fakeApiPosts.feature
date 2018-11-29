Feature: Fake API Posts

  Fake API: http://jsonplaceholder.typicode.com/
  /post used to create, retrieve and delete posts

  Steps utilises JSON Path dot notation: https://goessner.net/articles/JsonPath/

  Background:
    Given I am using the Fake API base URI

    # I need an @After hook here delete the new post afterwards
  Scenario: Add a new post
    When I request POST /posts with the following details:
      | $.userId | 4                                |
      | $.title  | Title of a new post              |
      | $.body   | This is the body of the new post |
    Then the response returns a HTTP 201 status code
#   Please note: usually you would check the item has been added using a GET call
#   but as this is a fake API it simply returns a HTTP 200 and doesn't add anything!
#   I have left the steps that you should use below:

#    And I GET /post/101
#    Then the response returns a HTTP 200 status code
#    And response contains the following:
#      | $.userId | 4                                |
#      | $.id     | 101                              |
#      | $.title  | Title of a new post              |
#      | $.body   | This is the body of the new post |

  # I need a @Before hook here to ensure that post/1 has been created
  # and a @After hook to delete it later
  Scenario: Update an existing post
    When I request PUT /posts/1 with the following details:
      | $.userId | 4                            |
      | $.title  | Updated title of post 1      |
      | $.body   | Updated body of the new post |
    Then the response returns a HTTP 200 status code

#   Please note: usually you would check the item has been updated using a GET call
#   but as this is a fake API it simply returns a HTTP 200 and doesn't update anything!
#   I have left the steps that you should use below:

#    And I GET /post/1
#    Then the response returns a HTTP 200 status code
#    And response contains the following:
#      | $.userId | 4                                |
#      | $.id     | 101                              |
#      | $.title  | Title of a new post              |
#      | $.body   | This is the body of the new post |

  # I need a @Before hook here to ensure there are posts populated in the system before I go this GET
  Scenario: Get all posts
    When I request GET /posts
    Then the response returns a HTTP 200 status code
    And the response has more than 1 entry

  # I would need a @Before hook here to ensure post 1 has been created before I attempt to GET it
  Scenario: Get a post by ID
    When I request GET /post/1
    Then the response returns a HTTP 200 status code
    And response matches the following:
      | $.userId | 1                                                                                                                                                                 |
      | $.id     | 1                                                                                                                                                                 |
      | $.title  | sunt aut facere repellat provident occaecati excepturi optio reprehenderit                                                                                        |
      | $.body   | quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto |

  # I would need a @Before hook here to ensure post 1 has been created before I attempt to DELETE it
  Scenario: Delete post by ID
    When I request DELETE /posts/1
    Then the response returns a HTTP 200 status code
#   Please note: usually you would check the item has been deleted
#   but as this is a fake API it simply returns a HTTP 200 and doesn't delete anything!
#   I have left the steps that you should use below:

#    And I request GET /post/1
#    Then the response returns a HTTP 404 status code
    
