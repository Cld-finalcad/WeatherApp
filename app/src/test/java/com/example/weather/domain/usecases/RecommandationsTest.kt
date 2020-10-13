package com.example.weather.domain.usecases

import com.example.weather.domain.models.Flag
import com.example.weather.domain.utils.Recommandations
import kotlinx.coroutines.runBlocking
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class RecommandationsTest {

    @Test
    fun `create params with rain main temperature above chilly and no wind, result should be no flag set`() = runBlocking {
        //arrange

        val main = "Rain"
        val temperature = 300.00
        val wind = 0.00

        // act
        val result = Recommandations.setFlags(main, temperature, wind)

        // assert
        assertThat(result).isEqualTo(mutableListOf<Flag>())
    }

    @Test
    fun `create params with clear main temperature above chilly and no wind, result should be only SUNNY flag set`() = runBlocking {
        //arrange


        val main = "Clear"
        val temperature = 300.00
        val wind = 0.00

        // act
        val result = Recommandations.setFlags(main, temperature, wind)

        // assert
        assertThat(result).isEqualTo(mutableListOf(Flag.SUNNY))
    }

    @Test
    fun `create params with rain main temperature equal to chilly but above cold temperature and no wind, result should be only CHILLY flag set`() = runBlocking {
        //arrange


        val main = "Rain"
        val temperature = 293.15
        val wind = 0.00

        // act
        val result = Recommandations.setFlags(main, temperature, wind)

        // assert
        assertThat(result).isEqualTo(mutableListOf(Flag.CHILLY))
    }

    @Test
    fun `create params with rain main temperature equal to cold and no wind, result should be COLD and CHILLY flag set`() = runBlocking {
        //arrange


        val main = "Rain"
        val temperature = 278.15
        val wind = 0.00

        // act
        val result = Recommandations.setFlags(main, temperature, wind)

        // assert
        assertThat(result).isEqualTo(mutableListOf(Flag.COLD, Flag.CHILLY))
    }


    @Test
    fun `create params with rain main temperature below cold and wind below windy speed, result should be COLD and CHILLY flag set`() = runBlocking {
        //arrange


        val main = "Rain"
        val temperature = 276.15
        val wind = 19.00

        // act
        val result = Recommandations.setFlags(main, temperature, wind)

        // assert
        assertThat(result).isEqualTo(mutableListOf<Flag>(Flag.COLD, Flag.CHILLY))
    }

    @Test
    fun `create params with rain main temperature equal to windy and wind below windy speed, result should be no flag set`() = runBlocking {
        //arrange


        val main = "Rain"
        val temperature = 298.15
        val wind = 19.00

        // act
        val result = Recommandations.setFlags(main, temperature, wind)

        // assert
        assertThat(result).isEqualTo(mutableListOf<Flag>())
    }

    @Test
    fun `create params with rain main temperature above windy and wind equal to windy speed, result should be no flag set`() = runBlocking {
        //arrange


        val main = "Rain"
        val temperature = 299.15
        val wind = 20.00

        // act
        val result = Recommandations.setFlags(main, temperature, wind)

        // assert
        assertThat(result).isEqualTo(mutableListOf<Flag>())
    }

    @Test
    fun `create params with rain main temperature below windy and wind above to windy speed, result should be WINDY set`() = runBlocking {
        //arrange


        val main = "Rain"
        val temperature = 297.15
        val wind = 21.00

        // act
        val result = Recommandations.setFlags(main, temperature, wind)

        // assert
        assertThat(result).isEqualTo(mutableListOf<Flag>(Flag.WINDY))
    }

    @Test
    fun `create params with rain main temperature equal to windy and wind above to windy speed, result should be WINDY set`() = runBlocking {
        //arrange


        val main = "Rain"
        val temperature = 298.15
        val wind = 21.00

        // act
        val result = Recommandations.setFlags(main, temperature, wind)

        // assert
        assertThat(result).isEqualTo(mutableListOf<Flag>(Flag.WINDY))
    }

    @Test
    fun `create params with rain main temperature below cold and wind above to windy speed, result should be WINDY COLD and CHILLY flags set`() {
        //arrange


        val main = "Rain"
        val temperature = 270.15
        val wind = 21.00

        // act
        val result = Recommandations.setFlags(main, temperature, wind)

        // assert
        assertThat(result).containsExactly(Flag.WINDY, Flag.CHILLY, Flag.COLD)
    }

    @Test
    fun `create params with clear main temperature below cold and wind above to windy speed, result should be every flags set`() {
        //arrange


        val main = "Clear"
        val temperature = 270.15
        val wind = 21.00

        // act
        val result = Recommandations.setFlags(main, temperature, wind)

        // assert
        assertThat(result).containsExactly(Flag.WINDY, Flag.CHILLY, Flag.COLD, Flag.SUNNY)
    }
}