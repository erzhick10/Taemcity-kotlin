import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildFeatures.swabra
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.dockerCommand
import jetbrains.buildServer.configs.kotlin.v2019_2.projectFeatures.githubConnection
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.vcs
import jetbrains.buildServer.configs.kotlin.v2019_2.vcs.GitVcsRoot

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

version = "2021.1"

project {

    vcsRoot(HttpsGithubComErzhick10httpPongRefsHeadsMaster)

    buildType(HttpPong)

    features {
        githubConnection {
            id = "PROJECT_EXT_2"
            displayName = "GitHub.com"
            clientId = "ffae6f2e8ce657c42a13"
            clientSecret = "credentialsJSON:26cf6a1f-7b28-4f14-a550-d62f395a73e9"
        }
    }
}

object HttpPong : BuildType({
    name = "http-pong"

    vcs {
        root(HttpsGithubComErzhick10httpPongRefsHeadsMaster)
    }

    steps {
        dockerCommand {
            commandType = build {
                source = file {
                    path = "Dockerfile"
                }
                namesAndTags = "%env.TEAMCITY_BUILDCONF_NAME%"
            }
        }
    }

    triggers {
        vcs {
        }
    }

    features {
        swabra {
        }
    }
})

object HttpsGithubComErzhick10httpPongRefsHeadsMaster : GitVcsRoot({
    name = "https://github.com/erzhick10/http-pong#refs/heads/master"
    url = "https://github.com/erzhick10/http-pong"
    branch = "refs/heads/master"
    branchSpec = "refs/heads/*"
    authMethod = password {
        userName = "erzhick10"
        password = "credentialsJSON:bc41456b-ad2c-40a5-a272-e84c63b8fd7e"
    }
})
