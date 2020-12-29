import com.lesfurets.jenkins.unit.declarative.DeclarativePipelineTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import static org.assertj.core.api.Java6Assertions.assertThat

class SamplePipelineTests extends DeclarativePipelineTest {
    private Object samplePipeline

    @Override
    @BeforeEach
    void setUp() {
        super.setUp()
        samplePipeline = loadScript('samplePipeline.groovy')
    }

    List executedStages() {
        List stages = []
        helper.callStack.findAll { call ->
            call.methodName == 'stage'
        }.every(call -> stages.add(call.args[0]))
        return stages
    }

    Boolean assertStagesExecution(List expectedStages) {
        assertThat(expectedStages).isEqualTo(executedStages())
    }

    @Test
    void "all needed stages executed"() {
        samplePipeline.call()
        println(helper.callStack)
        assertStagesExecution(['Build image'])
    }
}
