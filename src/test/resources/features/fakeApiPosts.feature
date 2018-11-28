Feature: Fake API Posts

  Fake API: http://jsonplaceholder.typicode.com/
  /post used to create, retrieve and delete posts

  Steps utilises JSON Path dot notation: https://goessner.net/articles/JsonPath/

  Background:
    Given I am using the Fake API base URI

  Scenario: Get all posts
    When I request GET /posts
    Then the response returns a HTTP 200 status code
    And the response has more than 1 entry

  Scenario: Get a post by ID
    When I request GET /post/1
    Then the response returns a HTTP 200 status code
    And response matches the following:
      | $.userId | 1                                                                                                                                                                 |
      | $.id     | 1                                                                                                                                                                 |
      | $.title  | sunt aut facere repellat provident occaecati excepturi optio reprehenderit                                                                                        |
      | $.body   | quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto |

  Scenario: Add a new post
    When I request POST /posts with the following details:
      | $.userId | 4                                |
      | $.title  | Title of a new post              |
      | $.body   | This is the body of the new post |
    Then the response returns a HTTP 201 status code
#   Please note: usually you would check the item has been added using a GET call
#   but as this is a fake API it simply returns a HTTP 200 and doesn't add anything!
#  I have left the steps that you should use below:

#    And I GET /post/101
#    Then the response returns a HTTP 200 status code
#    And response contains the following:
#      | $.userId | 4                                |
#      | $.id     | 101                              |
#      | $.title  | Title of a new post              |
#      | $.body   | This is the body of the new post |

  Scenario: Update an existing new post
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

  Scenario: Delete post by ID
    When I request DELETE /posts/1
    Then the response returns a HTTP 200 status code
#   Please note: usually you would check the item has been deleted
#   but as this is a fake API it simply returns a HTTP 200 and doesn't delete anything!
#   I have left the steps that you should use below:

#    And I request GET /post/1
#    Then the response returns a HTTP 404 status code
    
