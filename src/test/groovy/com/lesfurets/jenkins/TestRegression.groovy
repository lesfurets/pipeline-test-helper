package com.lesfurets.jenkins

import org.junit.Before
import org.junit.Test

import com.lesfurets.jenkins.helpers.BaseRegressionTest

class TestRegression extends BaseRegressionTest {

    @Override
    @Before
    void setUp() throws Exception {
        helper.baseScriptRoot = ""
        def scmBranch = "feature_test"
        binding.setVariable('scm', [
                        $class                           : 'GitSCM',
                        branches                         : [[name: scmBranch]],
                        doGenerateSubmoduleConfigurations: false,
                        extensions                       : [],
                        submoduleCfg                     : [],
                        userRemoteConfigs                : [[
                                                                            credentialsId: 'gitlab_git_ssh',
                                                                            url          : "github.com/lesfurets/pipeline-test-helper.git"
                                                            ]]
        ])
        super.setUp()
    }

    @Test
    void testNonReg() throws Exception {
        def script = loadScript("job/exampleJob.jenkins")
        script.execute()
        super.testNonRegression("example", false)
    }

}