package com.android.task.data.repository

import com.android.task.data.base.BaseTest
import com.android.task.data.base.FailedRequest
import com.android.task.data.dto.VehicleDTO
import com.android.task.data.dto.VehicleNoteDTO
import com.android.task.data.network.VehicleAPI
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test

import retrofit2.Response

class VehicleDataSourceImplTest : BaseTest<VehicleDataSourceImpl>() {

    override lateinit var classUnderTest: VehicleDataSourceImpl

    @MockK
    lateinit var vehicleApi: VehicleAPI

    @MockK
    lateinit var vehicleDataResponse: Response<List<VehicleDTO>>

    @MockK
    lateinit var vehicleNoteResponse: Response<List<VehicleNoteDTO>>

    @MockK
    lateinit var responseBody: ResponseBody

    @Before
    override fun setup() {
        super.setup()
        classUnderTest = VehicleDataSourceImpl(vehicleApi)
    }

    /**
     * GIVEN user exists on the API
     * WHEN I invoke the GetVehicles method
     * THEN I expect user object List<VehicleDTO> to be returned
     */
    @ExperimentalCoroutinesApi
    @Test
    fun testGetVehiclesSuccess() = runBlocking {
        coEvery { vehicleApi.getVehicles() } returns vehicleDataResponse
        coEvery { vehicleDataResponse.code() } returns 200
        coEvery { vehicleDataResponse.body() } returns getVehicleList()

        val vehicles = classUnderTest.getVehicles()
        assertNotNull(vehicles)
    }

    /**
     * GIVEN I have an invalid token
     * WHEN I invoke the GetVehicles method
     * THEN I expect InvalidTokenException to be thrown
     */
    @ExperimentalCoroutinesApi
    @Test
    fun testGetVehiclesInvalidToken() = runBlocking {
        coEvery { vehicleApi.getVehicles() } returns vehicleDataResponse
        coEvery { vehicleDataResponse.code() } returns 403
        coEvery { vehicleDataResponse.errorBody() } returns responseBody
        coEvery { responseBody.toString() } returns "Invalid token"

        try {
            classUnderTest.getVehicles()
            assertTrue(false)
        } catch (e: Exception) {
            assertTrue(true)
        }
    }

    /**
     * GIVEN I have not registered with C3LX
     * WHEN I invoke the GetVehicles method
     * THEN I expect NotFoundException to be thrown
     */
    @ExperimentalCoroutinesApi
    @Test
    fun testGetVehiclesNotFound() = runBlocking {
        coEvery { vehicleApi.getVehicles() } returns vehicleDataResponse
        coEvery { vehicleDataResponse.code() } returns 403
        coEvery { vehicleDataResponse.errorBody() } returns responseBody
        coEvery { responseBody.toString() } returns ""

        try {
            classUnderTest.getVehicles()
            assertTrue(false)
        } catch (e: Exception) {
            assertTrue(true)
        }
    }

    /**
     * GIVEN the C3LX servers returns response code that's not a 200/403/5XX
     * WHEN I invoke the GetVehicles method
     * THEN I expect FailedRequest to be thrown
     */
    @ExperimentalCoroutinesApi
    @Test
    fun testGetVehiclesFailedRequest() = runBlocking {
        coEvery { vehicleApi.getVehicles() } returns vehicleDataResponse
        coEvery { vehicleDataResponse.code() } returns 422
        try {
            classUnderTest.getVehicles()
            assertTrue(false)
        } catch (e: FailedRequest) {
            assertEquals(422, e.responseCode)
        }
    }

    /**
     * GIVEN user exists on the API
     * WHEN I invoke the getVehiclesNote method
     * THEN I expect user object List<VehicleNoteDTO> to be returned
     */
    @ExperimentalCoroutinesApi
    @Test
    fun testGetVehiclesNotesSuccess() = runBlocking {
        coEvery { vehicleApi.getVehiclesNote() } returns vehicleNoteResponse
        coEvery { vehicleNoteResponse.code() } returns 200
        coEvery { vehicleNoteResponse.body() } returns getVehicleNoteList()

        val vehicles = classUnderTest.getVehiclesNote()
        assertNotNull(vehicles)
    }

    /**
     * GIVEN I have an invalid token
     * WHEN I invoke the getVehiclesNote method
     * THEN I expect InvalidTokenException to be thrown
     */
    @ExperimentalCoroutinesApi
    @Test
    fun testGetVehiclesNotesInvalidToken() = runBlocking {
        coEvery { vehicleApi.getVehiclesNote() } returns vehicleNoteResponse
        coEvery { vehicleNoteResponse.code() } returns 403
        coEvery { vehicleNoteResponse.errorBody() } returns responseBody
        coEvery { responseBody.toString() } returns "Invalid token"

        try {
            classUnderTest.getVehiclesNote()
            assertTrue(false)
        } catch (e: Exception) {
            assertTrue(true)
        }
    }

    /**
     * GIVEN I have not registered with C3LX
     * WHEN I invoke the getVehiclesNote method
     * THEN I expect NotFoundException to be thrown
     */
    @ExperimentalCoroutinesApi
    @Test
    fun testGetVehiclesNotesNotFound() = runBlocking {
        coEvery { vehicleApi.getVehiclesNote() } returns vehicleNoteResponse
        coEvery { vehicleNoteResponse.code() } returns 403
        coEvery { vehicleNoteResponse.errorBody() } returns responseBody
        coEvery { responseBody.toString() } returns ""

        try {
            classUnderTest.getVehiclesNote()
            assertTrue(false)
        } catch (e: Exception) {
            assertTrue(true)
        }
    }

    /**
     * GIVEN the C3LX servers returns response code that's not a 200/403/5XX
     * WHEN I invoke the getVehiclesNote method
     * THEN I expect FailedRequest to be thrown
     */
    @ExperimentalCoroutinesApi
    @Test
    fun testGetVehiclesNotesFailedRequest() = runBlocking {
        coEvery { vehicleApi.getVehiclesNote() } returns vehicleNoteResponse
        coEvery { vehicleNoteResponse.code() } returns 422
        try {
            classUnderTest.getVehiclesNote()
            assertTrue(false)
        } catch (e: FailedRequest) {
            assertEquals(422, e.responseCode)
        }
    }


}

