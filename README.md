# 10 Minute Tutorial

[![Open in GitHub Codespaces](https://github.com/codespaces/badge.svg)](https://github.com/ttran375/comp311-cucumber-group9)

## Create an empty Cucumber project

We’ll start by creating a new project directory with the
`cucumber-archetype` Maven plugin. Open a terminal, go to the directory
where you want to create your project, and run the following command:

``` shell
mvn archetype:generate                      \
   "-DarchetypeGroupId=io.cucumber"           \
   "-DarchetypeArtifactId=cucumber-archetype" \
   "-DarchetypeVersion=7.18.1"               \
   "-DgroupId=hellocucumber"                  \
   "-DartifactId=hellocucumber"               \
   "-Dpackage=hellocucumber"                  \
   "-Dversion=1.0.0-SNAPSHOT"                 \
   "-DinteractiveMode=false"
```

You should get something like the following result:

``` shell
[INFO] Project created from Archetype in dir: <directory where you created the project>/cucumber
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
```

Change into the directory that was just created by running the following
command:

``` shell
cd hellocucumber
```

Open the project in IntelliJ IDEA:

- **File -\> Open… -\> (Select the pom.xml)**
- Select **Open as Project**

You now have a small project with Cucumber installed.

## Verify Cucumber installation

To make sure everything works together correctly, let’s run Cucumber.

``` shell
mvn test
```

You should see something like the following:

``` shell
-------------------------------------------------------
 T E S T S
-------------------------------------------------------

Tests run: 0, Failures: 0, Errors: 0, Skipped: 0

Results :

Tests run: 0, Failures: 0, Errors: 0, Skipped: 0

[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
```

Cucumber’s output is telling us that it didn’t find anything to run.

## Write a Scenario

When we do Behaviour-Driven Development with Cucumber we use *concrete
examples* to specify *what* we want the software to do. Scenarios are
written *before* production code. They start their life as an
*executable specification*. As the production code emerges, scenarios
take on a role as *living documentation* and *automated tests*.

Example Mapping

Try running an [Example Mapping](/docs/bdd/example-mapping) workshop in
your team to design examples together.

In Cucumber, an example is called a
[scenario](/docs/gherkin/reference#example). Scenarios are defined in
`.feature` files, which are stored in the
<span class="text-java">`src/test/resources/hellocucumber`</span>
<span class="is-hidden text-javascript">`features`</span>
<span class="is-hidden text-ruby">`features`</span> directory (or a
subdirectory).

One concrete example would be that *Sunday isn’t Friday*.

Create an empty file called
<span class="text-java">`src/test/resources/hellocucumber/is_it_friday_yet.feature`</span><span class="is-hidden text-kotlin">`src/test/resources/hellocucumber/is_it_friday_yet.feature`</span>
<span class="is-hidden text-javascript">`features/is_it_friday_yet.feature`</span>
<span class="is-hidden text-ruby">`features/is_it_friday_yet.feature`</span>
with the following content:

``` gherkin
Feature: Is it Friday yet?
  Everybody wants to know when it's Friday

  Scenario: Sunday isn't Friday
    Given today is Sunday
    When I ask whether it's Friday yet
    Then I should be told "Nope"
```

The first line of this file starts with the keyword `Feature:` followed
by a name. It’s a good idea to use a name similar to the file name.

The second line is a brief description of the feature. Cucumber does not
execute this line because it’s documentation.

The fourth line, `Scenario: Sunday is not Friday` is a
[scenario](/docs/gherkin/reference#example), which is a *concrete
example* illustrating how the software should behave.

The last three lines starting with `Given`, `When` and `Then` are the
[steps](/docs/gherkin/reference#example) of our scenario. This is what
Cucumber will execute.

## See scenario reported as undefined

Now that we have a scenario, we can ask Cucumber to execute it.

``` shell
mvn test
```

Cucumber is telling us we have one `undefined` scenario and three
`undefined` steps. It’s also suggesting some snippets of code that we
can use to *define* these steps:

``` shell
-------------------------------------------------------
 T E S T S
-------------------------------------------------------
Running hellocucumber.RunCucumberTest

Scenario: Sunday isn't Friday        # hellocucumber/is_it_friday_yet.feature:4
  Given today is Sunday
  When I ask whether it's Friday yet
  Then I should be told "Nope"
┌───────────────────────────────────────────────────────────────────────────────────┐
│ Share your Cucumber Report with your team at https://reports.cucumber.io          │
│ Activate publishing with one of the following:                                    │
│                                                                                   │
│ src/test/resources/cucumber.properties:          cucumber.publish.enabled=true    │
│ src/test/resources/junit-platform.properties:    cucumber.publish.enabled=true    │
│ Environment variable:                            CUCUMBER_PUBLISH_ENABLED=true    │
│ JUnit:                                           @CucumberOptions(publish = true) │
│                                                                                   │
│ More information at https://cucumber.io/docs/cucumber/environment-variables/      │
│                                                                                   │
│ Disable this message with one of the following:                                   │
│                                                                                   │
│ src/test/resources/cucumber.properties:          cucumber.publish.quiet=true      │
│ src/test/resources/junit-platform.properties:    cucumber.publish.quiet=true      │
└───────────────────────────────────────────────────────────────────────────────────┘
[ERROR] Tests run: 1, Failures: 0, Errors: 1, Skipped: 0, Time elapsed: 0.15 s <<< FAILURE! - in hellocucumber.RunCucumberTest
[ERROR] Is it Friday yet?.Sunday isn't Friday  Time elapsed: 0.062 s  <<< ERROR!
io.cucumber.junit.platform.engine.UndefinedStepException: 
The step 'today is Sunday' and 2 other step(s) are undefined.
You can implement these steps using the snippet(s) below:

@Given("today is Sunday")
public void today_is_sunday() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}
@When("I ask whether it's Friday yet")
public void i_ask_whether_it_s_friday_yet() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}
@Then("I should be told {string}")
public void i_should_be_told(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}
```

Copy each of the three snippets for the undefined steps and paste them
into
<span class="text-java">`src/test/java/hellocucumber/StepDefinitions.java`</span>

## See scenario reported as pending

Run Cucumber again. This time the output is a little different:

``` shell
-------------------------------------------------------
 T E S T S
-------------------------------------------------------
Running hellocucumber.RunCucumberTest
Feature: Is it Friday yet?
  Everybody wants to know when it's Friday

  Scenario: Sunday isn't Friday        # hellocucumber/is_it_friday_yet.feature:4
    Given today is Sunday              # Stepdefs.today_is_Sunday()
      io.cucumber.java.PendingException: TODO: implement me
 at hellocucumber.Stepdefs.today_is_Sunday(StepDefinitions.java:14)
 at ?.today is Sunday(classpath:hellocucumber/is_it_friday_yet.feature:5)

    When I ask whether it's Friday yet # Stepdefs.i_ask_whether_it_s_Friday_yet()
    Then I should be told "Nope"       # Stepdefs.i_should_be_told(String)

Pending scenarios:
hellocucumber/is_it_friday_yet.feature:4 # Sunday isn't Friday

1 Scenarios (1 pending)
3 Steps (2 skipped, 1 pending)
0m0.188s

io.cucumber.java.PendingException: TODO: implement me
 at hellocucumber.Stepdefs.today_is_Sunday(StepDefinitions.java:13)
 at ?.today is Sunday(classpath:hellocucumber/is_it_friday_yet.feature:5)
```

Cucumber found our step definitions and executed them. They are
currently marked as *pending*, which means we need to make them do
something useful.

## See scenario reported as failing

The next step is to do what the comments in the step definitions is
telling us to do:

> Write code here that turns the phrase above into concrete actions

Try to use the same words in the code as in the steps.

Ubiquitous Language

If the words in your steps originated from conversations during an
[Example Mapping](/docs/bdd/example-mapping) session, you’re building a
[Ubiquitous
Language](https://martinfowler.com/bliki/UbiquitousLanguage.html), which
we believe is a great way to make your production code and tests more
understandable and easier to maintain.

Change your step definition code to this:

``` java
package hellocucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.jupiter.api.Assertions.*;

class IsItFriday {
    static String isItFriday(String today) {
        return null;
    }
}

public class Stepdefs {
    private String today;
    private String actualAnswer;

    @Given("today is Sunday")
    public void today_is_Sunday() {
        today = "Sunday";
    }

    @When("I ask whether it's Friday yet")
    public void i_ask_whether_it_s_Friday_yet() {
        actualAnswer = IsItFriday.isItFriday(today);
    }

    @Then("I should be told {string}")
    public void i_should_be_told(String expectedAnswer) {
        assertEquals(expectedAnswer, actualAnswer);
    }
}
```

Run Cucumber again:

``` shell
-------------------------------------------------------
 T E S T S
-------------------------------------------------------
Running hellocucumber.RunCucumberTest
Feature: Is it Friday yet?
  Everybody wants to know when it's Friday

  Scenario: Sunday isn't Friday        # hellocucumber/is_it_friday_yet.feature:4
    Given today is Sunday              # Stepdefs.today_is_Sunday()
    When I ask whether it's Friday yet # Stepdefs.i_ask_whether_it_s_Friday_yet()
    Then I should be told "Nope"       # Stepdefs.i_should_be_told(String)
      java.lang.AssertionError: expected:<Nope> but was:<null>
 at org.junit.Assert.fail(Assert.java:88)
 at org.junit.Assert.failNotEquals(Assert.java:834)
 at org.junit.Assert.assertEquals(Assert.java:118)
 at org.junit.Assert.assertEquals(Assert.java:144)
 at hellocucumber.Stepdefs.i_should_be_told(StepDefinitions.java:31)
 at ?.I should be told "Nope"(classpath:hellocucumber/is_it_friday_yet.feature:7)


Failed scenarios:
hellocucumber/is_it_friday_yet.feature:4 # Sunday isn't Friday

1 Scenarios (1 failed)
3 Steps (1 failed, 2 passed)
0m0.404s
```

That’s progress! The first two steps are passing, but the last one is
failing.

## See scenario reported as passing

Let’s do the minimum we need to make the scenario pass. In this case,
that means making our
<span class="text-java">method</span><span class="is-hidden text-javascript">function</span><span class="is-hidden text-ruby">block</span><span class="is-hidden text-kotlin">function</span><span class="is-hidden text-scala">function</span>
return `Nope`:

``` java
static String isItFriday(String today) {
    return "Nope";
}
```

Run Cucumber again:

``` shell
-------------------------------------------------------
 T E S T S
-------------------------------------------------------
Running hellocucumber.RunCucumberTest
Feature: Is it Friday yet?
  Everybody wants to know when it's Friday

  Scenario: Sunday isn't Friday        # hellocucumber/is_it_friday_yet.feature:4
    Given today is Sunday              # Stepdefs.today_is_Sunday()
    When I ask whether it's Friday yet # Stepdefs.i_ask_whether_it_s_Friday_yet()
    Then I should be told "Nope"       # Stepdefs.i_should_be_told(String)

1 Scenarios (1 passed)
3 Steps (3 passed)
0m0.255s
```

Congratulations! You’ve got your first green Cucumber scenario.

## Add another failing test

The next thing to test for would be that we also get the correct result
when it *is* Friday.

Update the `is_it_friday_yet.feature` file:

``` gherkin
Feature: Is it Friday yet?
  Everybody wants to know when it's Friday

  Scenario: Sunday isn't Friday
    Given today is Sunday
    When I ask whether it's Friday yet
    Then I should be told "Nope"

  Scenario: Friday is Friday
    Given today is Friday
    When I ask whether it's Friday yet
    Then I should be told "TGIF"
```

We’ll need to add a step definition to set `today` to “Friday”:

``` java
@Given("today is Friday")
public void today_is_Friday() {
    today = "Friday";
}
```

When we run this test, it will fail.

``` shell
-------------------------------------------------------
 T E S T S
-------------------------------------------------------
Running hellocucumber.RunCucumberTest
Feature: Is it Friday yet?
  Everybody wants to know when it's Friday

  Scenario: Sunday isn't Friday        # hellocucumber/is_it_friday_yet.feature:4
    Given today is Sunday              # Stepdefs.today_is_Sunday()
    When I ask whether it's Friday yet # Stepdefs.i_ask_whether_it_s_Friday_yet()
    Then I should be told "Nope"       # Stepdefs.i_should_be_told(String)

  Scenario: Friday is Friday           # hellocucumber/is_it_friday_yet.feature:9
    Given today is Friday              # Stepdefs.today_is_Friday()
    When I ask whether it's Friday yet # Stepdefs.i_ask_whether_it_s_Friday_yet()
    Then I should be told "TGIF"       # Stepdefs.i_should_be_told(String)
      org.junit.ComparisonFailure: expected:<[TGIF]> but was:<[Nope]>
 at org.junit.Assert.assertEquals(Assert.java:115)
 at org.junit.Assert.assertEquals(Assert.java:144)
 at hellocucumber.Stepdefs.i_should_be_told(StepDefinitions.java:36)
 at ?.I should be told "TGIF"(classpath:hellocucumber/is_it_friday_yet.feature:12)


Failed scenarios:
hellocucumber/is_it_friday_yet.feature:9 # Friday is Friday

2 Scenarios (1 failed, 1 passed)
6 Steps (1 failed, 5 passed)
0m0.085s

org.junit.ComparisonFailure: expected:<[TGIF]> but was:<[Nope]>
 at org.junit.Assert.assertEquals(Assert.java:115)
 at org.junit.Assert.assertEquals(Assert.java:144)
 at hellocucumber.Stepdefs.i_should_be_told(StepDefinitions.java:36)
 at ?.I should be told "TGIF"(classpath:hellocucumber/is_it_friday_yet.feature:12)
```

That is because we haven’t implemented the logic yet! Let’s do that
next.

## Make it pass

We should update our statement to actually evaluate whether or not
`today` is equal to `"Friday"`.

``` java
static String isItFriday(String today) {
    return "Friday".equals(today) ? "TGIF" : "Nope";
}
```

Run Cucumber again:

``` shell
-------------------------------------------------------
 T E S T S
-------------------------------------------------------
Running hellocucumber.RunCucumberTest
Feature: Is it Friday yet?
  Everybody wants to know when it's Friday

  Scenario: Sunday isn't Friday        # hellocucumber/is_it_friday_yet.feature:4
    Given today is Sunday              # Stepdefs.today_is_Sunday()
    When I ask whether it's Friday yet # Stepdefs.i_ask_whether_it_s_Friday_yet()
    Then I should be told "Nope"       # Stepdefs.i_should_be_told(String)

  Scenario: Friday is Friday           # hellocucumber/is_it_friday_yet.feature:9
    Given today is Friday              # Stepdefs.today_is_Friday()
    When I ask whether it's Friday yet # Stepdefs.i_ask_whether_it_s_Friday_yet()
    Then I should be told "TGIF"       # Stepdefs.i_should_be_told(String)

2 Scenarios (2 passed)
6 Steps (6 passed)
0m0.255s
```

## Using variables and examples

So, we all know that there are more days in the week than just Sunday
and Friday. Let’s update our scenario to use variables and evaluate more
possibilities. We’ll use variables and examples to evaluate Friday,
Sunday, and anything else!

Update the `is_it_friday_yet.feature` file. Notice how we go from
`Scenario` to `Scenario Outline` when we start using multiple
`Examples`.

``` gherkin
Feature: Is it Friday yet?
  Everybody wants to know when it's Friday

  Scenario Outline: Today is or is not Friday
    Given today is "<day>"
    When I ask whether it's Friday yet
    Then I should be told "<answer>"

  Examples:
    | day            | answer |
    | Friday         | TGIF   |
    | Sunday         | Nope   |
    | anything else! | Nope   |
```

We need to replace the step definitions for `today is Sunday` and
`today is Friday` with one step definition that takes the value of
`<day>` as a String. Update the
<span class="text-java">`StepDefinitions.java`</span><span class="is-hidden text-javascript">`stepdefs.js`</span><span class="is-hidden text-ruby">`stepdefs.rb`</span>
file as follows:

``` java
package hellocucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.jupiter.api.Assertions.*;

class IsItFriday {
    static String isItFriday(String today) {
        return "Friday".equals(today) ? "TGIF" : "Nope";
    }
}

public class Stepdefs {
    private String today;
    private String actualAnswer;

    @Given("today is {string}")
    public void today_is(String today) {
        this.today = today;
    }

    @When("I ask whether it's Friday yet")
    public void i_ask_whether_it_s_Friday_yet() {
        actualAnswer = IsItFriday.isItFriday(today);
    }

    @Then("I should be told {string}")
    public void i_should_be_told(String expectedAnswer) {
        assertEquals(expectedAnswer, actualAnswer);
    }
}
```

Run Cucumber again:

``` shell
-------------------------------------------------------
 T E S T S
-------------------------------------------------------
Running hellocucumber.RunCucumberTest
Feature: Is it Friday yet?
  Everybody wants to know when it's Friday

  Scenario Outline: Today is or is not Friday # hellocucumber/is_it_friday_yet.feature:4
    Given today is "<day>"
    When I ask whether it's Friday yet
    Then I should be told "<answer>"

    Examples:

  Scenario Outline: Today is or is not Friday # hellocucumber/is_it_friday_yet.feature:11
    Given today is "Friday"                   # Stepdefs.today_is(String)
    When I ask whether it's Friday yet        # Stepdefs.i_ask_whether_it_s_Friday_yet()
    Then I should be told "TGIF"              # Stepdefs.i_should_be_told(String)

  Scenario Outline: Today is or is not Friday # hellocucumber/is_it_friday_yet.feature:12
    Given today is "Sunday"                   # Stepdefs.today_is(String)
    When I ask whether it's Friday yet        # Stepdefs.i_ask_whether_it_s_Friday_yet()
    Then I should be told "Nope"              # Stepdefs.i_should_be_told(String)

  Scenario Outline: Today is or is not Friday # hellocucumber/is_it_friday_yet.feature:13
    Given today is "anything else!"           # Stepdefs.today_is(String)
    When I ask whether it's Friday yet        # Stepdefs.i_ask_whether_it_s_Friday_yet()
    Then I should be told "Nope"              # Stepdefs.i_should_be_told(String)

3 Scenarios (3 passed)
9 Steps (9 passed)
0m0.255s
```

## Refactoring

Now that we have working code, we should do some refactoring:

- We should move the `isItFriday`
    <span class="text-java">method</span><span class="is-hidden text-javascript">function</span><span class="is-hidden text-ruby">block</span><span class="is-hidden text-kotlin">function</span><span class="is-hidden text-scala">function</span>
    out from the test code into production code.

- We could at some point extract helper methods from our step
    definition, for
    <span class="text-java">methods</span><span class="is-hidden text-kotlin">functions</span><span class="is-hidden text-javascript">functions</span><span class="is-hidden text-ruby">blocks</span>
    we use in several places.

## Summary

In this brief tutorial you’ve seen how to install Cucumber, how to
follow the BDD process to develop a
<span class="text-java">method</span><span class="is-hidden text-javascript">function</span><span class="is-hidden text-ruby">block</span><span class="is-hidden text-kotlin">function</span><span class="is-hidden text-scala">function</span>,
and how to use that
<span class="text-java">method</span><span class="is-hidden text-javascript">function</span><span class="is-hidden text-ruby">block</span><span class="is-hidden text-kotlin">function</span><span class="is-hidden text-scala">function</span>
to evaluate multiple scenarios!