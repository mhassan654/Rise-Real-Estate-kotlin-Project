package com.saavatech.riserealestate.util

import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey

//
object Constants {
    const val BASE_URL = "http://192.168.0.105:8000/api/" // "https://ebroker.wrteam.me/api/"
    const val AUTH_PREFERENCES = "AUTH_PREF"
    const val USER_SETTINGS = "userSettings"
    const val APP_ENTRY = "appEntry"
    const val USER = "user"

    val AUTH_KEY = stringSetPreferencesKey("auth_key")
    val USER_KEY = stringPreferencesKey("user_key")
    val ONBOARDING_KEY = stringSetPreferencesKey("onboarding_key")
}

object ApiConstants {
    const val API_LOGIN = "user_signup"
    const val API_UPDATE_PROFILE = "update_profile"
    const val API_GET_SLIDER = "get_slider"
    const val API_GET_CATEGORIES = "get_categories"
    const val API_GET_UNIT = "get_unit"
    const val API_GET_PROPERTY = "get_property"
    const val API_POST_PROPERTY = "post_property"
    const val API_UPDATE_PROPERTY = "update_post_property"
    const val API_GET_HOUSE_TYPE = "get_house_type"
    const val API_GET_NOTIFICATION_LIST = "get_notification_list"
    const val SET_PROPERTY_VIEW = "set_property_total_click"
    const val API_DELETE_USER = "delete_user"
    const val PAYPAL = "paypal"
    const val GET_ARTICLES = "get_articles"
    const val GET_COUNT_BY_CITIES_CATEGORY = "get_count_by_cities_categoris"
    const val GET_NEARBY_PROPERTIES = "get_nearby_properties"
    const val ADD_FAVOURITE = "add_favourite"
    const val REMOVE_FAVOURITE = "delete_favourite"
    const val GET_PACKAGE = "get_package"
    const val USER_PURCHASE_PACKAGE = "user_purchase_package"
    const val DELETE_INQUIRY = "delete_inquiry"
    const val GET_LANGUAGE = "get_languages"
    const val GET_PAYMENT_API_KEYS = "get_payment_settings"
    const val API_GET_SYSTEM_SETTINGS = "get_system_settings"
    const val API_SET_PROPERTY_ENQUIRY = "set_property_inquiry"
    const val API_GET_PROPERTY_ENQUIRY = "get_property_inquiry"
    const val API_GET_NOTIFICATIONS = "get_notification_list"
    const val API_REMOVE_POST_IMAGES = "remove_post_images"
    const val API_GET_USER_BY_ID = "get_user_by_id"
    const val GET_FAVOURITE_PROPERTY = "get_favourite_property"
    const val INTERESTED_USERS = "interested_users"
    const val STORE_ADVERTISEMENT = "store_advertisement"
    const val GET_LIMITS_OF_PACKAGE = "get_limits"
    const val GET_PAYMENT_DETAILS = "get_payment_details"
    const val DELETE_ADVERTISEMENT = "delete_advertisement"
    const val UPDATE_PROPERTY_STATUS = "update_property_status"
    const val DELETE_CHAT_MESSAGE = "delete_chat_message"
    const val GET_OUTDOOR_FACILITES = "get_facilities"
    const val GET_REPORT_REASONS = "get_report_reasons"
    const val ADD_REPORTS = "add_reports"
    const val ADD_EDIT_USER_INTEREST = "add_edit_user_interest"
    const val GET_USER_RECOMMENDATION = "get_user_recommendation"
    const val ASSIGN_FREE_PACKAGE = "assign_free_package"
}
