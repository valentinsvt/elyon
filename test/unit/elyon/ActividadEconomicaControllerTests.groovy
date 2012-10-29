package elyon



import org.junit.*
import grails.test.mixin.*

@TestFor(ActividadEconomicaController)
@Mock(ActividadEconomica)
class ActividadEconomicaControllerTests {


    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/actividadEconomica/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.actividadEconomicaInstanceList.size() == 0
        assert model.actividadEconomicaInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.actividadEconomicaInstance != null
    }

    void testSave() {
        controller.save()

        assert model.actividadEconomicaInstance != null
        assert view == '/actividadEconomica/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/actividadEconomica/show/1'
        assert controller.flash.message != null
        assert ActividadEconomica.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/actividadEconomica/list'


        populateValidParams(params)
        def actividadEconomica = new ActividadEconomica(params)

        assert actividadEconomica.save() != null

        params.id = actividadEconomica.id

        def model = controller.show()

        assert model.actividadEconomicaInstance == actividadEconomica
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/actividadEconomica/list'


        populateValidParams(params)
        def actividadEconomica = new ActividadEconomica(params)

        assert actividadEconomica.save() != null

        params.id = actividadEconomica.id

        def model = controller.edit()

        assert model.actividadEconomicaInstance == actividadEconomica
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/actividadEconomica/list'

        response.reset()


        populateValidParams(params)
        def actividadEconomica = new ActividadEconomica(params)

        assert actividadEconomica.save() != null

        // test invalid parameters in update
        params.id = actividadEconomica.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/actividadEconomica/edit"
        assert model.actividadEconomicaInstance != null

        actividadEconomica.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/actividadEconomica/show/$actividadEconomica.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        actividadEconomica.clearErrors()

        populateValidParams(params)
        params.id = actividadEconomica.id
        params.version = -1
        controller.update()

        assert view == "/actividadEconomica/edit"
        assert model.actividadEconomicaInstance != null
        assert model.actividadEconomicaInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/actividadEconomica/list'

        response.reset()

        populateValidParams(params)
        def actividadEconomica = new ActividadEconomica(params)

        assert actividadEconomica.save() != null
        assert ActividadEconomica.count() == 1

        params.id = actividadEconomica.id

        controller.delete()

        assert ActividadEconomica.count() == 0
        assert ActividadEconomica.get(actividadEconomica.id) == null
        assert response.redirectedUrl == '/actividadEconomica/list'
    }
}
