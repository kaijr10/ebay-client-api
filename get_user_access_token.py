"""
Link: https://developer.ebay.com/api-docs/static/oauth-authorization-code-grant.html
Get user access token follows 2 step:
Step 1: Get user constent
Step 2: Exchanging the authorization code for a User access token
"""
import requests
import base64
from urllib.parse import unquote

CLIENT_ID = "PhanKhan-testapp-SBX-190eb715c-ccabcbb1"
CLIENT_SERCET = "SBX-90eb715cfb3f-8b51-4557-9ea0-fdee"

SCOPE = [
    "https://api.ebay.com/oauth/api_scope/sell.inventory",
    "https://api.ebay.com/oauth/api_scope",
    "https://api.ebay.com/oauth/api_scope/sell.marketing",
    "https://api.ebay.com/oauth/api_scope/sell.fulfillment",
    "https://api.ebay.com/oauth/api_scope/sell.item",
    "https://api.ebay.com/oauth/api_scope/buy.order.readonly"
]

RU_NAME = "Phan_Khang-PhanKhan-testap-turqiheke"

"""
Get user constent.
Note:
 + redirect_uri: the RuName value for the environment. Link https://developer.ebay.com/api-docs/static/oauth-redirect-uri.html
"""
def get_user_constent():
    authorize_url = "https://auth.sandbox.ebay.com/oauth2/authorize"
    scope = ' '.join(SCOPE)
    params = {
        "client_id": CLIENT_ID,
        "redirect_uri": RU_NAME,
        "response_type": "code",
        "scope": scope,
    }

    response = requests.get(authorize_url, params=params)
    if response.status_code == 200:
        print(response.url)
        print("Please open the link above on the browser and process to get the authorization code")
        get_user_access_token()
    else:
        print("ERROR: Failed to authorize")

def get_user_access_token():
    code = str(input("Please enter authorization code which get from the above step:"))
    code = unquote(code)
    oauth_token_url = "https://api.sandbox.ebay.com/identity/v1/oauth2/token"
    headers = {
        "Content-Type": "application/x-www-form-urlencoded",
        "Authorization": "Basic " + base64_encoded()
    }
    body = {
        "grant_type": "authorization_code",
        "code": code,
        "redirect_uri": RU_NAME
    }

    response = requests.post(oauth_token_url, headers=headers, data=body)

    print(response.content)

    if response.status_code == 200:
        print(response.content)
    else:
        print("ERROR: Failed to get access token")

def base64_encoded():
    value = CLIENT_ID + ":" + CLIENT_SERCET
    value_byte = value.encode('ascii')
    b64_byte = base64.b64encode(value_byte)

    return b64_byte.decode('ascii')

def decode_access_token():
    print(unquote("v^1.1#i^1#r^0#f^0#p^3#I^3#t^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"))

if __name__ == '__main__':
    get_user_constent()
    #decode_access_token()
    #print(base64_encoded())