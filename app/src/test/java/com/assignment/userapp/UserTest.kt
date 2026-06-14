package com.assignment.userapp

import com.assignment.userapp.data.model.User
import junit.framework.TestCase.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

class UserTest {
    @Test
    fun `given all the fields of the User object should create User object with all the fields`() {
        val user = buildFullUser()
        assertEquals(1, user.id)
        assertEquals("Fabian Blick", user.name)
        assertEquals("Moean, Becker and Berge", user.company)
        assertEquals("Kory56", user.username)
        assertEquals("Ivory17@gmail.com", user.email)
        assertEquals("365 Linnie island", user.address)
        assertEquals("71974", user.zip)
        assertEquals("Kanas", user.state)
        assertEquals("Croatia", user.country)
        assertEquals("1-253-378-5405", user.phone)
        assertEquals("https://noimage", user.photo)
    }

    @Test
    fun `given all the fields of the User object with null values user fields should accept null fields`() {
        val user = User(
            id = null,
            name = null,
            company = null,
            username = null,
            email = null,
            address = null,
            zip = null,
            state = null,
            country = null,
            phone = null,
            photo = null,
        )
        assertEquals(null, user.id)
        assertEquals(null, user.name)
        assertEquals(null, user.company)
        assertEquals(null, user.username)
        assertEquals(null, user.email)
        assertEquals(null, user.address)
        assertEquals(null, user.zip)
        assertEquals(null, user.state)
        assertEquals(null, user.country)
        assertEquals(null, user.phone)
        assertEquals(null, user.photo)
    }

    @Test
    fun `given two users with same fields should return true`() {
        val user1 = buildFullUser()
        val user2 = buildFullUser()
        assertEquals(user1, user2)

    }

    @Test
    fun `given two users with different id should not be equal`() {
        val user1 = buildFullUser()
        val user2 = buildFullUser().copy(id = 2)
        assertNotEquals(user1, user2)
    }

    @Test
    fun `given two users with different name should not be equal`() {
        val user1 = buildFullUser()
        val user2 = buildFullUser().copy(name = "Fabian")
        assertNotEquals(user1, user2)
    }

    @Test
    fun `given a user object should not be equal to null`() {
        val user1 = buildFullUser()
        val user2 = null
        assertNotEquals(user1, user2)
    }

    @Test
    fun `given two same user objects should has same hashcode`() {
        val user1 = buildFullUser()
        val user2 = buildFullUser()
        assertEquals(user1.hashCode(), user2.hashCode())
    }

    @Test
    fun `given a user object and its field is updated then the field value should be updated`() {
        val user1 = buildFullUser()
        val user2 = user1.copy(name = "Fabian")
        assertEquals("Fabian", user2.name)
    }

}


private fun buildFullUser() = User(
    id = 1,
    name = "Fabian Blick",
    company = "Moean, Becker and Berge",
    username = "Kory56",
    email = "Ivory17@gmail.com",
    address = "365 Linnie island",
    zip = "71974",
    state = "Kanas",
    country = "Croatia",
    phone = "1-253-378-5405",
    photo = "https://noimage",

    )